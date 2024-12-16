package com.hongyan.toutiao.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户分页查询
 *
 * @author dhb
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageRequest extends PageRequest {

    private String username;

    private Integer gender;

    private Integer role;

    private Boolean enable;

}
