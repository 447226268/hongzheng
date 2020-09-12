package com.yxr.hz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.yxr.hz.dao")
@SpringBootApplication
public class HongzhengApplication {

    public static void main(String[] args) {
        SpringApplication.run(HongzhengApplication.class, args);
    }

}
