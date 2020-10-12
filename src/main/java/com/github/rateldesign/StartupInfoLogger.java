package com.github.rateldesign;

import com.github.rateldesign.common.util.StopWatch;
import org.slf4j.Logger;

import java.lang.management.ManagementFactory;

/**
 * @Description: 应用启动时日志
 * @Author stephen
 * @Date 2020/10/12 4:41 下午
 */
public class StartupInfoLogger {

    private final Class<?> sourceClass;

    StartupInfoLogger(Class<?> sourceClass) {
        this.sourceClass = sourceClass;
    }

    /**
     * 输出启动耗时日志
     * @param applicationLog 日志输出对象
     * @param stopWatch 启动计时器
     * @author stephen
     * @date 2020/10/12 5:01 下午
     */
    void logStarted(Logger applicationLog, StopWatch stopWatch) {
        applicationLog.info(getStartedMessage(stopWatch));
    }

    private String getStartedMessage(StopWatch stopWatch) {
        StringBuilder message = new StringBuilder();
        message.append("Started ");
        message.append("Started ");
        appendApplicationName(message);
        message.append(" in ");
        message.append(stopWatch.getTotalTimeMillis() / 1000.0);
        message.append(" seconds");
        try {
            double uptime = ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0;
            message.append(" (JVM running for ").append(uptime).append(")");
        }
        catch (Throwable ex) {
            // No JVM time available
        }
        return message.toString();
    }

    private void appendApplicationName(StringBuilder message) {
        String name = (this.sourceClass != null) ? sourceClass.getSimpleName() : "application";
        message.append(name);
    }

}
