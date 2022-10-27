package com.nfgj.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author nanfgj
 * @create 2022-09-22 9:31
 */
@EnableDiscoveryClient //nacos启动
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  // exclude 排除，  排除数据源自动配置类
@ComponentScan(basePackages = {"com.nfgj"})
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
