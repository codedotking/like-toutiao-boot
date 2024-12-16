package com.hongyan.toutiao.model.vo;

import com.hongyan.toutiao.model.db.Profile;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

/**
 * 更新用户信息
 */
@Data
@AutoMapper(target = Profile.class)
public class UpdateProfileRequest{


    private Long id;

    private Integer gender;

    private String address;

    private String email;

    private String nickName;
}
