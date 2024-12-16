package com.hongyan.toutiao.model.vo;

import com.hongyan.toutiao.model.db.Permission;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 创建权限
 */
@Data
@AutoMapper(target = Permission.class)
public class CreatePermissionRequest{

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private String type;

    private Long parentId;

    private String path;

    private String redirect;

    private String icon;

    private String component;

    private String layout;

    private Boolean keepalive;

    private String method;

    private String description;

    private Boolean show;

    private Boolean enable;

    private Integer order;
}
