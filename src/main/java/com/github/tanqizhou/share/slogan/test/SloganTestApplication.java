package com.github.tanqizhou.share.slogan.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/04/22/15:33
 * @Description:
 */
@SpringBootApplication
@EnableCaching
public class SloganTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SloganTestApplication.class, args);
    }
}
