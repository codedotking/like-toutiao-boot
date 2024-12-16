package com.hongyan.toutiao.model.request;

import lombok.Data;

import java.util.List;

/**
 * 给角色分配用户
 *
 * @author dhb
 */
@Data
public class AddRoleUsersRequest {

    private List<Long> userIds;

}