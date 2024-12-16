package com.hongyan.toutiao.model.request;

import lombok.Data;

import java.util.List;

/**
 * 添加角色权限
 *
 * @author dhb
 */
@Data
public class AddRolePermissionsRequest {

    private Long id;

    private List<Long> permissionIds;
}
