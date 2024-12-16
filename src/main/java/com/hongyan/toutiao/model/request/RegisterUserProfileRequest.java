package com.hongyan.toutiao.model.request;

import com.hongyan.toutiao.model.db.Profile;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

/**
 * 注册用户的用户信息
 */
@Data
@AutoMapper(target = Profile.class)
public class RegisterUserProfileRequest {

    private String nickName;

    private Integer gender;

    private String avatar;

    private String address;

    private String email;


}
