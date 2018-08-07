package com.kingboy.common.tools.exception;


import com.kingboy.common.utils.uuid.UUIDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/8/1 下午5:22
 * @desc 异常工厂.
 */
@Component
@PropertySource(value = "exception.properties", encoding = "UTF-8")
public class ExceptionManager {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.cloud.client.ipAddress}")
    private String ip;

    @Resource
    Environment environment;

    /**
     * 创建默认的异常
     * @param code
     * @return
     */
    public PhantomException create(String code) {
        return new PhantomException(UUIDUtils.getUUID(), appName, ip, code, environment.getProperty(code));
    }

    /**
     * 简化异常栈信息
     * @param pe
     * @return
     */
    protected PhantomException create(PhantomException pe) {
        List<StackTraceElement> traceList = Stream.of(pe.getStackTrace())
                .filter(p -> p.getClassName().contains("com.kingboy"))
                .filter(p -> !p.getClassName().contains("$"))
                .filter(p -> !p.getClassName().contains(".exception."))
                .collect(Collectors.toList());
        pe.setStackTrace(traceList.toArray(new StackTraceElement[]{}));
        return pe;
    }

}
