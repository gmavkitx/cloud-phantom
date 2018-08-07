package com.kingboy.common.tools.log;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/19 下午10:10
 * @desc 方法接收的参数日志.
 */
@Log4j
@Aspect
@Component
@ConditionalOnProperty(name = "king.log.enabled", havingValue = "true")
public class ParamInfoLog {

    @Pointcut("execution(* com.kingboy.controller..*(..))")
    public void controller() { }

    @Pointcut("execution(* com.kingboy.service..*(..))")
    public void service() { }

    @Pointcut("execution(* com.kingboy.repository..*(..))")
    public void repository() { }

    @Before("controller()")
    public void controller(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Long count = Stream.of(signature.getMethod().getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType() == RequestMapping.class)
                .count();
        String requestPath = count >= 1 ? signature.getMethod().getAnnotation(RequestMapping.class).value()[0] : "";

        String info = String.format("path:%s | %s", requestPath, getMethodInfo(point));
        log.info(info);
    }

    @Before("service()")
    public void service(JoinPoint point) {
        log.info(String.format("%s", getMethodInfo(point)));
    }

    @Before("repository()")
    public void repository(JoinPoint point) {
        log.info(String.format("%s", getMethodInfo(point)));
    }

    private String getMethodInfo(JoinPoint point) {
        String className = point.getSignature().getDeclaringType().getSimpleName();
        String methodName = point.getSignature().getName();
        String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
        StringBuilder sb = null;
        if (Objects.nonNull(parameterNames)) {
            sb = new StringBuilder();
            for (int i = 0; i < parameterNames.length; i++) {
                String value = point.getArgs()[i] != null ? point.getArgs()[i].toString() : "null";
                sb.append(parameterNames[i] + ":" + value + "; ");
            }
        }
        sb = sb == null ? new StringBuilder() : sb;
        String info = String.format("class:%s | method:%s | args:%s", className, methodName, sb.toString());
        return info;
    }
}
