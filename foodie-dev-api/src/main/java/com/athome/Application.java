package com.athome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/6/28 12:10
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.athome.mapper")
@ComponentScan(basePackages = {"org.n3r.idworker" ,"com.athome"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
