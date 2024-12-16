package com.hongyan.toutiao.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.NumberWithFormat;
import com.hongyan.toutiao.annotation.auth.RoleType;
import com.hongyan.toutiao.annotation.auth.Roles;
import com.hongyan.toutiao.annotation.preview.Preview;
import com.hongyan.toutiao.config.SaTokenConfigure;
import com.hongyan.toutiao.model.dto.UserDetailDto;
import com.hongyan.toutiao.model.dto.UserPageDto;
import com.hongyan.toutiao.model.exception.BizException;
import com.hongyan.toutiao.model.request.*;
import com.hongyan.toutiao.model.res.BizResponseCode;
import com.hongyan.toutiao.model.res.Page;
import com.hongyan.toutiao.model.res.R;
import com.hongyan.toutiao.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户Controller
 *
 * @author dhb
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "用户")
public class UserController {

    private final IUserService userService;


    /**
     * 新建用户
     *
     * @return R
     */
    @PostMapping
    @Roles({RoleType.SUPER_ADMIN})
    @Preview
    @Operation(summary = "新增用户")
    public R<Void> create(@RequestBody @Validated RegisterUserRequest request) {
        userService.register(request);
        return R.ok();
    }


    /**
     * 获取所有
     *
     * @return R
     */
    @GetMapping
    @Operation(summary = "获取所有")
    public R<Page<UserPageDto>> findAll(UserPageRequest request) {
        Page<UserPageDto> ret = userService.queryPage(request);
        return R.ok(ret);
    }


    /**
     * 根据id删除
     *
     * @return R
     */
    @DeleteMapping("{id}")
    @Roles({RoleType.SUPER_ADMIN})
    @Preview
    @Operation(summary = "根据id删除")
    public R<Void> remove(@PathVariable Long id) {
        NumberWithFormat userIdFormat = (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        if (userIdFormat.longValue() == id) {
            throw new BizException(BizResponseCode.ERR_11006, "非法操作，不能删除自己！");
        }
        userService.removeUser(id);
        return R.ok();
    }


    /**
     * 根据id更新
     *
     * @return R
     */
    @PatchMapping("{id}")
    @Preview
    @Operation(summary = "根据id更新")
    public R<Void> update(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        userService.updateById(id, request);
        return R.ok();
    }


    /**
     * 更新资料
     *
     * @param id id
     * @return R
     */
    @PatchMapping("/profile/{id}")
    @Preview
    @Operation(summary = "更新资料")
    public R<Void> updateProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        NumberWithFormat userIdFormat = (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        if (userIdFormat.longValue() != id) {
            throw new BizException(BizResponseCode.ERR_11004, "越权操作，用户资料只能本人修改！");
        }
        userService.updateProfile(id, request);
        return R.ok();
    }


    /**
     * 获取用户详细信息
     * <p>
     * 此方法用于获取当前登录用户的详细信息它首先从SaToken中获取用户ID和角色代码，
     * 然后调用UserService的detail方法获取用户详细信息，并以HTTP响应的形式返回
     *
     * @return 返回一个包含用户详细信息的响应对象
     */
    @GetMapping("/detail")
    @Operation(summary = "用户信息")
    public R<UserDetailDto> detail() {
        // 从SaToken中获取用户ID
        NumberWithFormat userId =
                (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        // 从SaToken中获取用户角色代码
        String roleCode = (String) StpUtil.getExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY);
        // 调用UserService的detail方法，传入用户ID和角色代码，获取用户详细信息
        UserDetailDto detail = userService.detail(userId.longValue(), roleCode);
        // 返回包含用户详细信息的响应对象
        return R.ok(detail);
    }


    /**
     * 根据用户名获取
     *
     * @param username 用户名
     * @return R
     */
    @GetMapping("/{username}")
    @Roles({RoleType.SUPER_ADMIN})
    @Operation(summary = "根据用户名获取")
    public R<Void> findByUsername(@PathVariable String username) {
        throw new BizException(BizResponseCode.ERR_11006, "接口未实现");
    }


    /**
     * 查询用户的profile
     *
     * @return R
     */
    @GetMapping("/profile/{userId}")
    @Operation(summary = "查询用户的profile")
    public R<Void> getUserProfile(@PathVariable Long userId) {
        throw new BizException(BizResponseCode.ERR_11006, "接口未实现");
    }

    /**
     * 添加角色
     *
     * @param userId 用户id
     * @return R
     */
    @PostMapping("/roles/add/{userId}")
    @Preview
    @Operation(summary = "添加角色")
    public R<Object> addRoles(@PathVariable Long userId, @RequestBody @Validated AddUserRolesRequest request) {
        userService.addRoles(userId, request);
        return R.ok();
    }

    /**
     * 重置密码
     *
     * @return R
     */
    @PatchMapping("password/reset/{userId}")
    @Roles({RoleType.SUPER_ADMIN})
    @Operation(summary = "重置密码")
    public R<Object> resetPassword(@PathVariable Long userId, @RequestBody @Validated UpdatePasswordRequest request) {
        userService.resetPassword(userId, request);
        return R.ok();
    }
}
