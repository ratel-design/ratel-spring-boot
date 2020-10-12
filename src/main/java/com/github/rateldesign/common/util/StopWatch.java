package com.github.rateldesign.common.util;

/**
 * @Description: 计时器
 * @Author stephen
 * @Date 2020/10/10 5:15 下午
 */
public class StopWatch {

    /**
     * 计时器状态
     * 0: init
     * 1: start
     * 2: stop
     */
    private byte status = 0;
    /**
     * 开始时间纳秒数
     */
    private long startTimeNanos;
    /**
     * 计时总纳秒数
     */
    private long totalTimeNanos;

    /**
     * 开始计时
     * @author stephen
     * @date 2020/10/10 5:38 下午
     */
    public void start() {
        if (status != 0) {
            throw new IllegalStateException("Can't start StopWatch: it's not init");
        }
        status = 1;
        startTimeNanos = System.nanoTime();
    }

    /**
     * 结束计时
     * @author stephen
     * @date 2020/10/10 5:43 下午
     */
    public void stop() {
        if (status != 1) {
            throw new IllegalStateException("Can't start StopWatch: it's not start");
        }
        status = 2;
        totalTimeNanos = System.nanoTime() - startTimeNanos;
    }

    /**
     * 获取计时器的毫秒数
     * @return long
     * @author stephen
     * @date 2020/10/10 5:24 下午
     */
    public long getTotalTimeMillis() {
        return totalTimeNanos / 1000 / 1000;
    }

}
