package com.hongyan.toutiao.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import com.hongyan.toutiao.annotation.auth.RoleType;
import com.hongyan.toutiao.annotation.auth.Roles;
import com.hongyan.toutiao.config.SaTokenConfigure;
import com.hongyan.toutiao.model.db.Role;
import com.hongyan.toutiao.model.dto.PermissionDto;
import com.hongyan.toutiao.model.dto.RoleDto;
import com.hongyan.toutiao.model.dto.RolePageDto;
import com.hongyan.toutiao.model.vo.*;
import com.hongyan.toutiao.model.res.Page;
import com.hongyan.toutiao.model.res.R;
import com.hongyan.toutiao.service.IRoleService;
import io.github.linpeilie.Converter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色Controller
 */
@Tag(name = "角色")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    private final Converter converter;

    /**
     * 新建角色
     *
     * @return R
     */
    @PostMapping
    @Roles(RoleType.SUPER_ADMIN)
    @Operation(summary = "新增角色")
    public R<Void> create(@RequestBody @Validated CreateRoleRequest request) {
        roleService.createRole(request);
        return R.ok();
    }

    /**
     * 获取所有角色
     *
     * @return R
     */
    @GetMapping
    @Operation(summary = "获取所有角色")
    public R<List<RoleDto>> findAll(
            @RequestParam(value = "enable", required = false) Boolean enable
    ) {
        List<RoleDto> roleDtoList = roleService.lambdaQuery().eq(ObjectUtil.isNotNull(enable), Role::getEnable, enable)
                .list()
                .stream()
                .map(role -> converter.convert(role, RoleDto.class))
                .toList();
        return R.ok(roleDtoList);
    }

    /**
     * 分页
     *
     * @return R
     */
    @GetMapping("/page")
    @Operation(summary = "分页")
    public R<Page<RolePageDto>> findPagination(RolePageRequest request) {
        Page<RolePageDto> ret = roleService.queryPage(request);
        return R.ok(ret);
    }

    /**
     * 查询角色权限
     *
     * @return R
     */
    @GetMapping("/permissions")
    @Operation(summary = "查询角色权限")
    public R<List<PermissionDto>> findRolePermissions(Long id) {
        List<PermissionDto> permissionDtoList = roleService.findRolePermissions(id);
        return R.ok(permissionDtoList);
    }


    /**
     * 根据id获取
     *
     * @return R
     */
    @GetMapping("{id}")
    @Roles(RoleType.SUPER_ADMIN)
    @Operation(summary = "根据id获取")
    public R<RoleDto> findOne(@PathVariable Long id) {
        RoleDto roleDto = converter.convert(roleService.getById(id), RoleDto.class);
        return R.ok(roleDto);
    }


    /**
     * 根据id更新
     *
     * @return R
     */
    @PatchMapping("{id}")
    @Roles({RoleType.SUPER_ADMIN, RoleType.SYS_ADMIN, RoleType.ROLE_PMS})
    @Operation(summary = "根据id更新")
    public R<Void> update(@PathVariable Long id, @RequestBody UpdateRoleRequest request) {
        roleService.updateRole(id, request);
        return R.ok();
    }


    /**
     * 根据id删除
     *
     * @return R
     */
    @DeleteMapping("{id}")
    @Roles({RoleType.SUPER_ADMIN})
    @Operation(summary = "根据id删除")
    public R<Void> remove(@PathVariable Long id) {
        roleService.removeRole(id);
        return R.ok();
    }


    /**
     * 给角色添加权限
     *
     * @return R
     */
    @PostMapping("/permissions/add")
    @Roles({RoleType.SUPER_ADMIN})
    @Operation(summary = "给角色添加权限")
    public R<Void> addRolePermissions(@RequestBody @Validated AddRolePermissionsRequest request) {
        roleService.addRolePermissions(request);
        return R.ok();
    }

    /**
     * 角色的权限树
     *
     * @return R
     */
    @GetMapping("/permissions/tree")
    @Operation(summary = "角色的权限树")
    public R<List<Tree<Long>>> permissionTree() {
        String roleCode = (String) StpUtil.getExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY);
        List<Tree<Long>> treeList = roleService.findRolePermissionsTree(roleCode);
        return R.ok(treeList);
    }


    /**
     * 给角色分配用户
     *
     * @return R
     */
    @PatchMapping("/users/add/{roleId}")
    @Roles({RoleType.SUPER_ADMIN})
    @Operation(summary = "给角色分配用户")
    public R<Void> addRoleUsers(@PathVariable Long roleId, @RequestBody AddRoleUsersRequest request) {
        roleService.addRoleUsers(roleId, request);
        return R.ok();
    }


    /**
     * 移除指定角色的用户
     * <p>
     * 该接口用于移除与指定角色ID关联的用户它接受一个角色ID和一个包含用户ID的请求体，
     * 并将这些用户从指定角色中移除只有超级管理员有权限执行此操作
     *
     * @param roleId  角色ID，用于指定需要移除用户的角色
     * @param request 包含需要移除的用户ID的请求对象
     * @return 返回一个表示操作结果的响应对象，如果操作成功，返回R.ok()
     */
    @PatchMapping("/users/remove/{roleId}")
    @Roles({RoleType.SUPER_ADMIN})
    @Operation(summary = "移除角色")
    public R<Void> removeRoleUsers(@PathVariable Long roleId, @RequestBody RemoveRoleUsersRequest request) {
        roleService.removeRoleUsers(roleId, request);
        return R.ok();
    }

}
