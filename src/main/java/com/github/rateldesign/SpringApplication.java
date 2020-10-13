package com.github.rateldesign;

import com.github.rateldesign.common.Banner;
import com.github.rateldesign.common.util.Assert;
import com.github.rateldesign.common.util.StopWatch;
import org.slf4j.LoggerFactory;

/**
 * @Description: 启动类
 * @Author stephen
 * @Date 2020/10/10 4:53 下午
 */
public class SpringApplication {
    
    private final Class<?> mainClass;

    private SpringApplication(Class<?> mainClass) {
        this.mainClass = mainClass;
    }

    public static void run(Class<?> mainClass) {
        new SpringApplication(mainClass).run();
    }

    public void run() {
        Assert.notNull(mainClass, "not run, class is null");
        Banner.print(mainClass, System.out);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // TODO:MQH 2020/10/12 实现AOP
        // TODO:MQH 2020/10/12 实现IOC
        // TODO:MQH 2020/10/12 实现 Autowired、Service注解 解决循环引用
        // TODO:MQH 2020/10/12 实现HTTP框架和 RestController、GetMapping 注解
        stopWatch.stop();
        new StartupInfoLogger(mainClass).logStarted(LoggerFactory.getLogger(mainClass), stopWatch);
    }

}
