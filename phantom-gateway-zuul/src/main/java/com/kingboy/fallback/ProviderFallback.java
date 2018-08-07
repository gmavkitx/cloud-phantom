package com.kingboy.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/22 下午8:17
 * @desc 生产服务回退.
 */
@Component
public class ProviderFallback implements ZuulFallbackProvider {
    @Override
    public String getRoute() {
        return "phantom-service-provider";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(("<html>"
                        + "<head><title>ERROR</title></head>"
                        + "<body>"
                        + "<h1 style='text-align: center;margin: 100px;padding: 50px;background-color: lightskyblue;'>"
                        + "您所访问的服务目前无法提供服务！"
                        + "</h1>"
                        + "</body>"
                        + "</html>").getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
                return headers;
            }
        };
    }
}
