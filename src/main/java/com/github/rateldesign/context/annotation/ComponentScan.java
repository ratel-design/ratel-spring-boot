package com.github.rateldesign.context.annotation;

import java.lang.annotation.*;

/**
 * 包扫描注解
 *
 * @author stephen
 * @date 2020/10/20 5:48 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ComponentScan {

    /**
     * @return 扫描的包路径
     */
    String[] value() default {};
}
