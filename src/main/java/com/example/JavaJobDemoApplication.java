package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 程序入口
 */
@SpringBootApplication
@EnableScheduling//开启任务调度
public class JavaJobDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaJobDemoApplication.class, args);
    }

}
