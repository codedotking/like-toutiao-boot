package com.hongyan.toutiao.model.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hongyan.toutiao.model.dto.RoleDto;
import com.hongyan.toutiao.model.dto.RolePageDto;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;

/**
 * 角色
 *
 * @author dhb
 */
@Data
@TableName("role")
@AutoMappers({
        @AutoMapper(target = RoleDto.class),
        @AutoMapper(target = RolePageDto.class)
})
public class Role {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String code;

    private String name;

    private Boolean enable;

}
