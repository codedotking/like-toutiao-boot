package com.hongyan.toutiao.service.impl;

import cn.hutool.cache.Cache;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.lang.UUID;
import com.hongyan.toutiao.service.ICaptchaService;
import org.springframework.stereotype.Service;

/**
 * 验证码，单机模式
 */
@Service
public class CaptchaServiceImpl implements ICaptchaService {

    private final Cache<String, ICaptcha> captchaList = new TimedCache<>(60000);

    /**
     * 创建一个圆形验证码对象，并生成一个唯一的键值对存储它
     * <p>
     * 此方法首先利用CaptchaUtil工具类创建一个圆形验证码对象captcha，然后生成一个随机的UUID作为键，
     * 并将该键值对存储在captchaList中，以便后续验证使用最后，方法返回一个包含键和验证码对象的Pair，
     * 用于传递给调用者
     *
     * @return Pair对象，其中包含生成的唯一键和对应的圆形验证码对象
     */
    @Override
    public Pair<String, ICaptcha> create() {
        // 创建圆形验证码对象，参数分别为宽度、高度、字符数和干扰元素数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(80, 40, 4, 4);
        // 生成随机UUID作为验证码的唯一键
        String key = UUID.randomUUID().toString(true);
        // 将生成的键值对存储在Map中，以便后续验证
        captchaList.put(key, captcha);
        // 返回包含键和验证码对象的Pair
        return Pair.of(key, captcha);
    }

    /**
     * 验证验证码的正确性
     * 此方法用于校验用户输入的验证码是否与系统生成的验证码匹配
     * 它首先根据验证码的键从列表中获取对应的验证码对象，然后移除该验证码对象，
     * 最后调用验证码对象的验证方法来判断用户输入的验证码是否正确
     *
     * @param key  验证码的唯一键，用于标识特定的验证码对象
     * @param code 用户输入的验证码字符串
     * @return 如果用户输入的验证码正确，则返回true；否则返回false
     */
    @Override
    public boolean verify(String key, String code) {
        // 根据键获取验证码对象
        ICaptcha captcha = captchaList.get(key);
        // 如果验证码对象为空，则返回false
        if (captcha == null) {
            return false;
        }
        // 验证码验证通过后，从列表中移除，以防止重复验证
        captchaList.remove(key);
        // 调用验证码对象的验证方法，判断用户输入的验证码是否正确
        return captcha.verify(code);
    }
}
