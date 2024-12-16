package com.hongyan.toutiao.service;

import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.lang.Pair;

/**
 * 验证码服务
 */
public interface ICaptchaService {

    /**
     * 创建验证码
     *
     * @return key与验证码
     */
    Pair<String, ICaptcha> create();

    /**
     * 验证码校验
     *
     * @param key  验证码key
     * @param code 验证码
     * @return 是否通过
     */
    boolean verify(String key, String code);

}
