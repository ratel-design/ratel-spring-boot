package com.github.rateldesign;

import com.github.rateldesign.common.Banner;
import com.github.rateldesign.common.util.StopWatch;
import com.github.rateldesign.context.ApplicationContext;
import com.github.rateldesign.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 启动类
 * @Author stephen
 * @Date 2020/10/10 4:53 下午
 */
public class SpringApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringApplication.class);
    
    private SpringApplication() {
    }

    public static void run(Class<?> mainClass, String ... args) {
        new SpringApplication().run(mainClass);
    }

    private void run(Class<?> mainClass) {
        Banner.print(mainClass, System.out);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        new ApplicationContext(mainClass).refresh();
        // TODO:MQH 2020/10/12 实现AOP
        // TODO:MQH 2020/10/12 实现IOC
        // TODO:MQH 2020/10/12 实现 Autowired、Service注解 解决循环引用
        // TODO:MQH 2020/10/12 实现HTTP框架和 RestController、GetMapping 注解
        stopWatch.stop();
        new StartupInfoLogger(mainClass).logStarted(LoggerFactory.getLogger(mainClass), stopWatch);
    }


}
