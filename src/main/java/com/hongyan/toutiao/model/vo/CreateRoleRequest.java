package com.hongyan.toutiao.model.vo;

import com.hongyan.toutiao.model.db.Role;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 创建角色
 */
@Data
@AutoMapper(target = Role.class)
public class CreateRoleRequest {

    @NotBlank(message = "角色编码不能为空")
    private String code;

    @NotBlank(message = "角色名不能为空")
    private String name;

    @NotEmpty(message = "权限不能为空")
    private List<Long> permissionIds;

    private Boolean enable;

}
