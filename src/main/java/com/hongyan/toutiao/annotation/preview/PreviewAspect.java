package com.hongyan.toutiao.annotation.preview;

import com.hongyan.toutiao.model.exception.BizException;
import com.hongyan.toutiao.model.res.BizResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * {@link Preview}切面处理
 *
 */
@Aspect
@Component
@ConditionalOnProperty(value = "pms.preview", havingValue = "true")
@Slf4j
public class PreviewAspect implements InitializingBean {


    @Override
    public void afterPropertiesSet() {
        log.info("已打开预览环境，限制部分功能.");
    }


    @Pointcut("@annotation(com.hongyan.toutiao.annotation.preview.Preview)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        throw new BizException(BizResponseCode.ERR_30001);
    }


}
