package com.hongyan.toutiao.model.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hongyan.toutiao.model.dto.PermissionDto;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

/**
 * 权限
 *
 * @author dhb
 */
@Data
@TableName("permission")
@AutoMapper(target = PermissionDto.class)
public class Permission{

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String code;

    private String type;

    @TableField("parentId")
    private Long parentId;

    private String path;

    private String redirect;

    private String icon;

    private String component;

    private String layout;

    @TableField("keepAlive")
    private Boolean keepAlive;

    private String method;

    private String description;

    @TableField("`show`")
    private Boolean show;

    private Boolean enable;

    @TableField("`order`")
    private Integer order;

}
