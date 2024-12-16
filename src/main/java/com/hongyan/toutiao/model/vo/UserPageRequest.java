package com.hongyan.toutiao.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户分页查询
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageRequest extends PageRequest {

    private String username;

    private Integer gender;

    private Integer role;

    private Boolean enable;

}
