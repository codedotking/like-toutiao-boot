package com.hongyan.toutiao.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 角色Dto
 *
 * @author dhb
 */
@Data
public class RolePageDto {

    private Long id;

    private String code;

    private String name;

    private Boolean enable;

    private List<Long> permissionIds;

}
