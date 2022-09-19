package com.nfgj.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author nanfgj
 * @create 2022-09-19 17:07
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.nfgj"})  //组件扫描
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
