package com.hongyan.toutiao.model.request;

import lombok.Data;

import java.util.List;

/**
 * 更新用户
 *
 * @author dhb
 */
@Data
public class UpdateUserRequest {

    private List<Long> roleIds;

    private Boolean enable;

}
