package com.kingboy.controller.refresh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/19 下午10:35
 * @desc 默认.
 */
@RefreshScope
@RestController
public class RefreshController {

    @Value("${king.refresh}")
    private String refresh;

    @GetMapping(value = "/value/refresh")
    public String refresh() {
        return refresh;
    }

}
