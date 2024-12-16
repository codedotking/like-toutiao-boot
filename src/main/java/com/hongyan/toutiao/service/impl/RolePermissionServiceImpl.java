package com.hongyan.toutiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyan.toutiao.mapper.RolePermissionMapper;
import com.hongyan.toutiao.model.db.RolePermission;
import com.hongyan.toutiao.service.IRolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
        implements IRolePermissionService {
}
