package com.github.rateldesign.common.util;

/**
 * @Description: 校验参数
 * @Author stephen
 * @Date 2020/10/12 3:32 下午
 */
public abstract class Assert {

    /**
     * 校验表达式为true
     * @param expression 表达式
     * @param message 异常消息-表达式不为true时
     * @author stephen
     * @date 2020/10/12 3:41 下午
     * @throws IllegalStateException 如果 {@code expression} 为 {@code false}
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 校验对象不为空
     * @param object 对象
     * @param message 异常消息-表达式不为true时
     * @author stephen
     * @date 2020/10/12 3:41 下午
     * @throws IllegalStateException 如果 {@code object} 为 {@code null}
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalStateException(message);
        }
    }

}
