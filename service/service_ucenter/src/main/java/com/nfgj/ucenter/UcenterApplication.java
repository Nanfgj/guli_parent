package com.nfgj.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author nanfgj
 * @create 2022-09-28 15:10
 */
@SpringBootApplication
@MapperScan("com.nfgj.ucenter.mapper")
@ComponentScan("com.nfgj")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
