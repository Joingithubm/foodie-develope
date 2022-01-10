package com.athome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description:
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-04 7:48 下午
 */
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class} )
//@MapperScan("com.athome.mapper")
public class SharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharingApplication.class,args);
    }
}
