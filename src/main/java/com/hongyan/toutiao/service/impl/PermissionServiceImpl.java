package com.hongyan.toutiao.service.impl;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyan.toutiao.lib.PermissionUtil;
import com.hongyan.toutiao.mapper.PermissionMapper;
import com.hongyan.toutiao.model.db.Permission;
import com.hongyan.toutiao.model.dto.PermissionDto;
import com.hongyan.toutiao.model.request.CreatePermissionRequest;
import com.hongyan.toutiao.service.IPermissionService;
import io.github.linpeilie.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限服务类的实现类，主要负责权限相关的处理
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements IPermissionService {

    private static final String TYPE_MENU = "MENU";
    private static final String TYPE_BUTTON = "BUTTON";


    private final Converter converter;

    @Override
    public List<Permission> findByRoleId(Long roleId) {
        return getBaseMapper().findByRoleId(roleId);
    }

    @Override
    public void create(CreatePermissionRequest request) {
        Permission permission = converter.convert(request, Permission.class);
        this.save(permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBatch(List<CreatePermissionRequest> request) {
        List<Permission> list = request.stream().map(dto -> converter.convert(dto, Permission.class))
                .toList();
        this.saveBatch(list);
    }

    @Override
    public List<PermissionDto> findAllMenu() {
        return lambdaQuery().eq(Permission::getType, TYPE_MENU)
                .list()
                .stream()
                .map(permission -> converter.convert(permission, PermissionDto.class))
                .toList();
    }

    @Override
    public List<Tree<Long>> findAllMenuTree() {
        List<Permission> permissions = lambdaQuery().eq(Permission::getType, TYPE_MENU)
                .orderByAsc(Permission::getOrder)
                .list()
                .stream()
                .toList();
        return PermissionUtil.toTreeNode(permissions, null);
    }

    @Override
    public List<Tree<Long>> findAllTree() {
        List<Permission> permissions = lambdaQuery()
                .orderByAsc(Permission::getOrder)
                .list()
                .stream()
                .toList();
        return PermissionUtil.toTreeNode(permissions, null);
    }

    @Override
    public List<Permission> findButton(Long parentId) {
        return lambdaQuery()
                .eq(Permission::getParentId, parentId)
                .in(Permission::getType, TYPE_BUTTON)
                .orderByAsc(Permission::getOrder)
                .list()
                .stream()
                .toList();
    }

    @Override
    public boolean validateMenuPath(String path) {
        return lambdaQuery().eq(Permission::getPath, path).exists();
    }

}
