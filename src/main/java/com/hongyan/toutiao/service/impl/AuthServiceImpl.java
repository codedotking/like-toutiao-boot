package com.hongyan.toutiao.service.impl;


import cn.dev33.satoken.stp.StpUtil;
import com.hongyan.toutiao.config.SaTokenConfigure;
import com.hongyan.toutiao.service.IAuthService;
import org.springframework.stereotype.Component;
import cn.hutool.core.collection.CollUtil;

import java.util.List;

@Component
public class AuthServiceImpl implements IAuthService {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        String role = (String) StpUtil.getExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY);

        return CollUtil.newArrayList(role);
    }
}