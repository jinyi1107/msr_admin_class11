package com.msr.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主类
 */
@SpringBootApplication
@MapperScan("com.msr.edu.mapper")
public class EduAppication {
    public static void main(String[] args) {
        SpringApplication.run(EduAppication.class);
    }
}