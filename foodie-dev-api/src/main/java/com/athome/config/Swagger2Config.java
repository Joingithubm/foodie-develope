package com.athome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: 配置接口文档
 * @Author Zengfc
 * @Date 2021/6/29 10:02
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    //原路径 htpp://localhost:8088/swagger-ui.html
    //bootstrap  http://localhost:8088/doc.html

    @Bean
    public Docket createDocket(){
        // 指定API类型
        return new Docket(DocumentationType.SWAGGER_2)
                    //api文档汇总信息
                    .apiInfo(createApiInfo())
                    .select()
                    .apis(RequestHandlerSelectors
                            //指定扫描的包
                            .basePackage("com.athome.controller"))
                        //所有的controller
                    .paths(PathSelectors.any())
                    .build();
    }

    private ApiInfo createApiInfo(){

        return new  ApiInfoBuilder()
                    .title("天天吃货api平台")
                    .contact(new Contact("zengfc","zengfc","1291@qq.com"))
                    .description("天天吃货后台api接口文档")
                    .version("0.0.1")
                    .termsOfServiceUrl("zengfc").build();
    }
}
