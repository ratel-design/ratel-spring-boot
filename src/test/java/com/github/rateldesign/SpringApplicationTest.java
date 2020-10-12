package com.github.rateldesign;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description:
 * @Author stephen
 * @Date 2020/10/12 5:23 下午
 */
class SpringApplicationTest {

    @DisplayName("启动应用")
    @Test
    void run() {
        SpringApplication.run(SpringApplicationTest.class);
    }
}