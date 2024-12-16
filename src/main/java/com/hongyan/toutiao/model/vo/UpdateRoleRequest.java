package com.hongyan.toutiao.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 更新角色
 *
 * @author dhb
 */
@Data
public class UpdateRoleRequest {

    private String name;

    private Boolean enable;

    private List<Long> permissionIds;


}
