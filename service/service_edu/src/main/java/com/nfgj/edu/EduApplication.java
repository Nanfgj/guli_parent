package com.nfgj.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author nanfgj
 * @create 2022-09-19 17:07
 */
@EnableDiscoveryClient //nacos注册
@EnableFeignClients //开启openfeign
@SpringBootApplication
@ComponentScan(basePackages = {"com.nfgj"})  //组件扫描
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
