package com.kingboy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/20 上午12:54
 * @desc  生产服务启动类.
 */
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class PhantomServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhantomServiceProviderApplication.class, args);
    }

    @RestController
    class IndexController {

        @Value("${spring.application.name}")
        private String applicationName;

        @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
        public String index() {
            return "<html>"
                    + "<head><title>" + applicationName + "</title></head>"
                    + "<body>"
                    + "<h1 style='text-align: center;margin: 100px;padding: 50px;background-color: lightskyblue;'>"
                    + "欢迎访问" + applicationName + "!"
                    + "</h1>"
                    + "</body>"
                    + "</html>";
        }

    }
}
