package com.kingboy;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监控程序
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class PhantomServerAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhantomServerAdminApplication.class, args);
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
