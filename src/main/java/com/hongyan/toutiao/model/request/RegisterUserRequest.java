package com.hongyan.toutiao.model.request;

import com.hongyan.toutiao.model.db.User;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 注册用户
 */
@Data
@AutoMapper(target = User.class)
public class RegisterUserRequest {

    @Length(min = 6, max = 20, message = "用户名长度必须是6到20之间")
    private String username;

    @Length(min = 6, max = 20, message = "密码长度必须是6到20之间")
    private String password;

    private Boolean enable;

    private RegisterUserProfileRequest profile;

    private List<Long> roleIds;


}
