package com.kingboy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/20 下午3:25
 * @desc 单应用监控.
 */
@EnableHystrixDashboard
@SpringBootApplication
public class PhantomServerHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhantomServerHystrixApplication.class, args);
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
