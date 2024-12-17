package com.hongyan.toutiao.controller;

import cn.hutool.core.lang.tree.Tree;
import com.hongyan.toutiao.annotation.preview.Preview;
import com.hongyan.toutiao.model.db.Permission;
import com.hongyan.toutiao.model.dto.PermissionDto;
import com.hongyan.toutiao.model.request.CreatePermissionRequest;
import com.hongyan.toutiao.model.request.UpdatePermissionRequest;
import com.hongyan.toutiao.model.res.R;
import com.hongyan.toutiao.service.IPermissionService;
import io.github.linpeilie.Converter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限Controller
 */
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@Tag(name = "权限")
public class PermissionController {


    private final IPermissionService permissionService;

    private final Converter converter;

    /**
     * 新建权限
     *
     * @return R
     */
    @PostMapping
    @Preview
    @Operation(summary = "新增权限")
    public R<Void> create(@RequestBody @Validated CreatePermissionRequest request) {
        permissionService.create(request);
        return R.ok();
    }


    /**
     * 批量创建权限
     *
     * @return R
     */
    @PostMapping("/batch")
    @Preview
    @Operation(summary = "批量新增权限")
    public R<Void> batchCreate(@RequestBody @Validated List<CreatePermissionRequest> request) {
        permissionService.createBatch(request);
        return R.ok();
    }

    /**
     * 获取所有权限
     *
     * @return R
     */
    @GetMapping
    @Operation(summary = "获取所有权限")
    public R<List<PermissionDto>> findAll() {
        List<PermissionDto> menu = permissionService.findAllMenu();
        return R.ok(menu);
    }

    /**
     * 获取所有权限树
     *
     * @return R
     */
    @GetMapping("/tree")
    @Operation(summary = "获取所有权限树")
    public R<List<Tree<Long>>> findAllTree() {
        List<Tree<Long>> tree = permissionService.findAllTree();
        return R.ok(tree);
    }

    /**
     * 获取菜单树
     *
     * @return R
     */
    @GetMapping("menu/tree")
    @Operation(summary = "获取菜单树")
    public R<List<Tree<Long>>> findMenuTree() {
        List<Tree<Long>> tree = permissionService.findAllMenuTree();
        return R.ok(tree);
    }

    /**
     * 根据id获取
     *
     * @return R
     */
    @GetMapping("{id}")
    @Operation(summary = "根据id获取")
    public R<PermissionDto> findOne(@PathVariable Long id) {
        PermissionDto permissionDto = converter.convert(permissionService.getById(id),PermissionDto.class);
        return R.ok(permissionDto);
    }

    /**
     * 根据id更新
     *
     * @return R
     */
    @PatchMapping("{id}")
    @Operation(summary = "根据id更新")
    public R<Object> update(@PathVariable Long id, @RequestBody UpdatePermissionRequest request) {
        Permission permission = converter.convert(request, Permission.class);
        permission.setId(id);
        permissionService.updateById(permission);
        return R.ok();
    }

    /**
     * 根据id删除
     *
     * @return R
     */
    @DeleteMapping("{id}")
    @Operation(summary = "根据id删除")
    public R<Object> remove(@PathVariable Long id) {
        permissionService.removeById(id);
        return R.ok();
    }


    /**
     * 获取
     *
     * @return R
     */
    @GetMapping("/button/{parentId}")
    @Operation(summary = "根据父id获取权限列表")
    public R<List<PermissionDto>> findButtonAndApi(@PathVariable Long parentId) {
        List<PermissionDto> permissions = permissionService.findButton(parentId)
            .stream().map(permission -> converter.convert(permission, PermissionDto.class))
            .toList();
        return R.ok(permissions);
    }

    /**
     * 校验 path 存不存在menu资源内
     *
     * @return R
     */
    @GetMapping("/menu/validate")
    @Operation(summary = "校验path存不存在menu资源内")
    public R<Object> validateMenuPath(String path) {
        boolean b = permissionService.validateMenuPath(path);
        return R.ok(b);
    }

}
