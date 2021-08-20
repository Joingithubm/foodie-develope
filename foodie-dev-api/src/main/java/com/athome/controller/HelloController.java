package com.athome.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/6/28 12:11
 * @Version 1.0
 */
@ApiIgnore
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello world!";
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }
}
