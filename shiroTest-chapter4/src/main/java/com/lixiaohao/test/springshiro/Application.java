package com.lixiaohao.test.springshiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by xiaohao.li on 2017/7/19.
 */
@SpringBootApplication
@Configuration
@ServletComponentScan
@ImportResource({"classpath:spring-application.xml","classpath:applicationContext-shiro.xml"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
