package com.kingboy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/22 下午3:10
 * @desc 服务网关.
 */
@EnableZuulProxy
@SpringBootApplication
public class PhantomGatewayZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhantomGatewayZuulApplication.class, args);
    }

    /**
     * 将zuul的url配置动态化
     *
     * @return
     */
    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
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
