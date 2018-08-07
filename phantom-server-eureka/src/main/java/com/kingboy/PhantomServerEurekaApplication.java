package com.kingboy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/20 上午12:54
 * @desc 注册中心启动类.
 */
@EnableEurekaServer
@SpringBootApplication
public class PhantomServerEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhantomServerEurekaApplication.class, args);
    }

}
