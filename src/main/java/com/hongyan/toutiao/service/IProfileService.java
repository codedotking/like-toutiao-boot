package com.hongyan.toutiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongyan.toutiao.model.db.Profile;

/**
 * ProfileService
 */
public interface IProfileService extends IService<Profile> {

    /**
     * 通过用户id获取用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    Profile findByUserId(Long userId);

}
