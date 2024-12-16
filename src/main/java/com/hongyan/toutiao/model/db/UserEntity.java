package com.hongyan.toutiao.model.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("User")
public class UserEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String verifiedContent;

    private Boolean userVerified;

    private int followerCount;

    private String avatarUrl;

    private String email;

    private String password;

    private LocalDateTime registrationDate;

    private LocalDateTime lastLogin;

    private String userId;
}