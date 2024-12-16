package com.hongyan.toutiao.annotation.preview;

import java.lang.annotation.*;

/**
 * 标识方法在预览环境是否关闭
 *
 * @author dhb
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Preview {

}
