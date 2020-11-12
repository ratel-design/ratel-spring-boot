package com.github.rateldesign.factory;

import com.github.rateldesign.exception.BeanException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description: 单例对象工厂
 * @Author stephen
 * @Date 2020/10/13 3:51 下午
 */
public class SingletonBeanRegistry {

    private static final Class<?>[] REGISTER_ANNOTATION = {};

    /**
     * 注册对象
     *
     * @param beanName 对象名称
     * @param registerClass 对象类
     * @return 注册对象
     * @author stephen
     * @date 2020/10/13 3:54 下午
     */
    public <T> T register(String beanName, Class<T> registerClass) {
        Constructor<T> constructor;
        try {
            constructor = registerClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new BeanException("not find constructor", e);
        }
        try {
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeanException("newInstance error", e);
        }
    }

    /**
     * 获取对象
     *
     * @param beanName 对象名称
     * @return java.lang.Object
     * @author stephen
     * @date 2020/10/13 3:58 下午
     */
    public Object getBean(String beanName) {
        return null;
    }
}
