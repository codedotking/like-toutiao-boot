package com.hongyan.toutiao.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户详细信息
 *
 * @author dhb
 */
@Data
public class UserDetailDto {

    private Long id;

    private String username;

    private Boolean enable;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private RoleDto currentRole;

    private ProfileDto profile;

    private List<RoleDto> roles;

}
