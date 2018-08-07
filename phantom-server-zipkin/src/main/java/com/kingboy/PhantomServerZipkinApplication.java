package com.kingboy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/22 下午3:10
 * @desc 日志调用链跟踪.
 */
@EnableZipkinStreamServer
@SpringBootApplication
public class PhantomServerZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhantomServerZipkinApplication.class, args);
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
