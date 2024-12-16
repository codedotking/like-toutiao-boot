package com.hongyan.toutiao.service.impl;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyan.toutiao.annotation.preview.PreviewProperties;
import com.hongyan.toutiao.config.SaTokenConfigure;
import com.hongyan.toutiao.mapper.UserMapper;
import com.hongyan.toutiao.model.db.Profile;
import com.hongyan.toutiao.model.db.Role;
import com.hongyan.toutiao.model.db.User;
import com.hongyan.toutiao.model.db.UserRole;
import com.hongyan.toutiao.model.dto.*;
import com.hongyan.toutiao.model.exception.BizException;
import com.hongyan.toutiao.model.request.*;
import com.hongyan.toutiao.model.res.BizResponseCode;
import com.hongyan.toutiao.model.res.Page;
import com.hongyan.toutiao.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.linpeilie.Converter;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final IRoleService roleService;

    private final IProfileService IProfileService;

    private final IUserRoleService userRoleService;

    private final ICaptchaService captchaService;

    private final PreviewProperties previewProperties;

    private final Converter converter;


    @Override
    public LoginTokenDto login(LoginRequest request) {
        User user = lambdaQuery().eq(User::getUsername, request.getUsername()).one();
        if (user == null) {
            throw new BizException(BizResponseCode.ERR_10002);
        }
        // 预览环境下可快速登录，不用验证码
        if (Boolean.TRUE.equals(request.getIsQuick()) && Boolean.TRUE.equals(previewProperties.getPreview())) {
            return login(request, user);
        }
        if (StrUtil.isBlank(request.getCaptchaKey())
                || !captchaService.verify(request.getCaptchaKey(), request.getCaptcha())) {
            throw new BizException(BizResponseCode.ERR_10003);
        }
        return login(request, user);
    }

    private LoginTokenDto login(LoginRequest request, User user) {
        log.info("login: {}", user);
        boolean checkPw = BCrypt.checkpw(request.getPassword(), user.getPassword());
        log.info("checkPw: {}", checkPw);
        if (checkPw) {
            // 查询用户的角色
            List<Role> roles = roleService.findRolesByUserId(user.getId());
            log.info("roles: {}", roles);
            return generateToken(user, roles, roles.isEmpty() ? "" : roles.getFirst().getCode());
        } else {
            throw new BizException(BizResponseCode.ERR_10002);
        }
    }

    @Override
    public UserDetailDto detail(Long userId, String roleCode) {
        User user = getById(userId);
        log.info("user: {}", user);
        UserDetailDto userDetailDto = converter.convert(user, UserDetailDto.class);
        log.info("userDetailDto: {}", userDetailDto);

        Profile userProfile = IProfileService.findByUserId(userId);
        ProfileDto profileDto = converter.convert(userProfile, ProfileDto.class);
        List<RoleDto> roleDtoList = roleService.findRolesByUserId(userId)
                .stream()
                .filter(Role::getEnable)
                .map(role -> converter.convert(role, RoleDto.class))
                .toList();
        if (roleDtoList.isEmpty()) {
            throw new BizException(BizResponseCode.ERR_11005);
        }
        userDetailDto.setProfile(profileDto);
        userDetailDto.setRoles(roleDtoList);
        for (RoleDto roleDto : roleDtoList) {
            log.info("roleDto: {}, roleCode :{}", roleDto, roleCode);
            if (roleDto.getCode().equals(roleCode)) {
                userDetailDto.setCurrentRole(roleDto);
                break;
            }
        }
        return userDetailDto;
    }

    @Override
    public LoginTokenDto switchRole(Long userId, String roleCode) {
        User user = getById(userId);
        List<Role> roles = roleService.findRolesByUserId(userId);
        Role currentRole = null;
        for (Role role : roles) {
            if (roleCode.equals(role.getCode())) {
                currentRole = role;
            }
        }
        if (currentRole == null) {
            throw new BizException(BizResponseCode.ERR_11005);
        }
        return generateToken(user, roles, currentRole.getCode());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterUserRequest request) {
        boolean exists = lambdaQuery().eq(User::getUsername, request.getUsername()).exists();
        if (exists) {
            throw new BizException(BizResponseCode.ERR_10001);
        }
        User user = converter.convert(request, User.class);
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        this.save(user);

        RegisterUserProfileRequest registerUserProfileRequest = Optional.ofNullable(request.getProfile()).orElse(new RegisterUserProfileRequest());
        Profile profile = converter.convert(registerUserProfileRequest, Profile.class);
        profile.setUserId(user.getId());
        if (CollUtil.isNotEmpty(request.getRoleIds())) {
            List<UserRole> roleList = request.getRoleIds().stream()
                    .map(roleId -> {
                        UserRole userRole = new UserRole();
                        userRole.setUserId(user.getId());
                        userRole.setRoleId(roleId);
                        return userRole;
                    }).toList();
            userRoleService.saveBatch(roleList);
        }
        IProfileService.save(profile);
    }

    @Override
    public LoginTokenDto refreshToken() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        StpUtil.login(tokenInfo.getLoginId(), SaLoginConfig
                .setExtra(SaTokenConfigure.JWT_USER_ID_KEY,
                        StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY))
                .setExtra(SaTokenConfigure.JWT_USERNAME_KEY,
                        StpUtil.getExtra(SaTokenConfigure.JWT_USERNAME_KEY))
                .setExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY,
                        StpUtil.getExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY))
                .setExtra(SaTokenConfigure.JWT_ROLE_LIST_KEY,
                        StpUtil.getExtra(SaTokenConfigure.JWT_ROLE_LIST_KEY))
        );
        SaTokenInfo newTokenInfo = StpUtil.getTokenInfo();
        LoginTokenDto dto = new LoginTokenDto();
        dto.setAccessToken(newTokenInfo.getTokenValue());
        return dto;
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        String username = (String) StpUtil.getExtra(SaTokenConfigure.JWT_USERNAME_KEY);
        User user = lambdaQuery().select(User::getPassword).eq(User::getUsername, username).one();
        if (!BCrypt.checkpw(request.getOldPassword(), user.getPassword())) {
            throw new BizException(BizResponseCode.ERR_10004);
        }
        user.setPassword(BCrypt.hashpw(request.getNewPassword()));
        lambdaUpdate().set(User::getPassword, BCrypt.hashpw(request.getNewPassword()))
                .eq(User::getUsername, username)
                .update();
        StpUtil.logout();
    }

    @Override
    public Page<UserPageDto> queryPage(UserPageRequest request) {
        IPage<User> qp = request.toPage();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(request.getUsername()), User::getUsername, request.getUsername())
                .or()
                .eq(ObjectUtil.isNotNull(request.getEnable()), User::getEnable, request.getEnable());

        IPage<UserPageDto> ret = getBaseMapper().pageDetail(qp,
                        request.getUsername(),
                        request.getGender(),
                        request.getEnable())
                .convert(dto -> {
                    List<RoleDto> roleDtoList = roleService.findRolesByUserId(dto.getId()).stream()
                            .map(role -> converter.convert(role, RoleDto.class)).toList();
                    dto.setRoles(roleDtoList);
                    return dto;
                });
        return Page.convert(ret);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeUser(Long id) {
        if (id == 1) {
            throw new BizException(BizResponseCode.ERR_11006, "不能删除根用户");
        }
        removeById(id);
        IProfileService.lambdaUpdate().eq(Profile::getUserId, id).remove();
    }

    @Override
    public void resetPassword(Long userId, UpdatePasswordRequest request) {
        String newPw = BCrypt.hashpw(request.getPassword());
        lambdaUpdate().eq(User::getId, userId)
                .set(User::getPassword, newPw)
                .update();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRoles(Long userId, AddUserRolesRequest request) {
        userRoleService.lambdaUpdate().eq(UserRole::getUserId, userId).remove();
        List<UserRole> list = request.getRoleIds().stream()
                .map(roleId -> {
                    UserRole userRole = new UserRole();
                    userRole.setUserId(userId);
                    userRole.setRoleId(roleId);
                    return userRole;
                }).toList();
        userRoleService.saveBatch(list);
    }

    @Override
    public void updateProfile(Long id, UpdateProfileRequest request) {
        Profile profile = converter.convert(request, Profile.class);
        IProfileService.updateById(profile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(Long id, UpdateUserRequest request) {
        if (request.getRoleIds() != null) {
            AddUserRolesRequest addUserRolesRequest = new AddUserRolesRequest();
            addUserRolesRequest.setRoleIds(request.getRoleIds());
            addRoles(id, addUserRolesRequest);
        }
        if (request.getEnable() != null) {
            lambdaUpdate().eq(User::getId, id)
                    .set(User::getEnable, request.getEnable())
                    .update();
        }
    }


    private LoginTokenDto generateToken(User user, List<Role> roles, String currentRoleCode) {
        // 密码验证成功
        StpUtil.login(user.getId(),
                SaLoginConfig.setExtra(SaTokenConfigure.JWT_USER_ID_KEY, user.getId())
                        .setExtra(SaTokenConfigure.JWT_USERNAME_KEY, user.getUsername())
                        .setExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY, currentRoleCode)
                        .setExtra(SaTokenConfigure.JWT_ROLE_LIST_KEY, roles));
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        LoginTokenDto dto = new LoginTokenDto();
        dto.setAccessToken(tokenInfo.getTokenValue());
        return dto;
    }

}
