package com.hongyan.toutiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongyan.toutiao.model.db.User;
import com.hongyan.toutiao.model.dto.LoginTokenDto;
import com.hongyan.toutiao.model.dto.UserDetailDto;
import com.hongyan.toutiao.model.dto.UserPageDto;
import com.hongyan.toutiao.model.vo.*;
import com.hongyan.toutiao.model.res.Page;

/**
 * User Service
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param request 请求
     * @return 返回token
     */
    LoginTokenDto login(LoginRequest request);

    /**
     * 用户信息
     *
     * @param userId   用户id
     * @param roleCode 用户角色编码
     * @return 用户信息
     */
    UserDetailDto detail(Long userId, String roleCode);

    /**
     * 切换角色
     *
     * @param userId   用户id
     * @param roleCode 角色编码
     * @return 切换后重新获取token
     */
    LoginTokenDto switchRole(Long userId, String roleCode);

    /**
     * 注册用户
     *
     * @param request 请求
     */
    void register(RegisterUserRequest request);

    /**
     * 刷新token
     */
    LoginTokenDto refreshToken();

    /**
     * 修改密码
     *
     * @param request 请求
     */
    void changePassword(ChangePasswordRequest request);

    /**
     * 分页查询
     *
     * @param request 请求
     * @return ret
     */
    Page<UserPageDto> queryPage(UserPageRequest request);

    /**
     * 根据id删除用户，不能删除自己
     *
     * @param id 用户id
     */
    void removeUser(Long id);

    /**
     * 重置密码
     *
     * @param userId  用户id
     * @param request 包含密码
     */
    void resetPassword(Long userId, UpdatePasswordRequest request);

    /**
     * 给用户分配角色
     *
     * @param userId  用户id
     * @param request 包含角色id
     */
    void addRoles(Long userId, AddUserRolesRequest request);

    /**
     * 更新用户信息
     *
     * @param id      用户id
     * @param request 用户信息
     */
    void updateProfile(Long id, UpdateProfileRequest request);

    /**
     * 更新用户
     *
     * @param id      用户id
     * @param request 用户信息
     */
    void updateById(Long id, UpdateUserRequest request);
}
