package com.hongyan.toutiao.model.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.hongyan.toutiao.model.dto.UserDetailDto;
import com.hongyan.toutiao.model.dto.UserPageDto;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;

/**
 * 用户
 *
 * @author dhb
 */
@Data
@TableName("`user`")
@AutoMappers({
        @AutoMapper(target = UserDetailDto.class),
        @AutoMapper(target = UserPageDto.class)
})
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private Boolean enable;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("updateTime")
    private LocalDateTime updateTime;

}
