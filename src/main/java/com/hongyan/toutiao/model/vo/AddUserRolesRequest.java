package com.hongyan.toutiao.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 给用户分配角色
 *
 * @author dhb
 */
@Data
public class AddUserRolesRequest {

    private List<Long> roleIds;

}
