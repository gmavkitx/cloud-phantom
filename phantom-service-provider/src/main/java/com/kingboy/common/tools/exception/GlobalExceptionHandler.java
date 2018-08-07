package com.kingboy.common.tools.exception;

import com.kingboy.common.utils.uuid.UUIDUtils;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/8/1 下午5:10
 * @desc 全局异常捕捉并转换异常
 */
@Log4j
@RestControllerAdvice(basePackages = "com.kingboy.controller")
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.cloud.client.ipAddress}")
    private String ip;

    @Resource
    ExceptionManager exceptionManager;

    /**
     * 校验异常
     * @param e
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handlerException(ConstraintViolationException e) {
        String code = e.getConstraintViolations().stream().limit(1).map(vio -> vio.getMessageTemplate())
                 .collect(Collectors.toList()).get(0);
        PhantomException exception = exceptionManager.create(code);
        exception.setStackTrace(e.getStackTrace());

        PhantomException phantomException = exceptionManager.create(exception);
        log.error(logTraceInfo(phantomException));
        return phantomException.toString();
    }

    /**
     * 如果是自定义异常
     * @param e
     */
    @ExceptionHandler(PhantomException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handlerException(PhantomException e) {
        PhantomException phantomException = exceptionManager.create(e);
        log.error(logTraceInfo(phantomException));
        return phantomException.toString();
    }

    @ExceptionHandler(HystrixRuntimeException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public String handleException(HystrixRuntimeException e) {
        String message = e.getFallbackException().getCause().getMessage();
        PhantomException exception = new PhantomException(UUIDUtils.getUUID(), appName, ip, "FALLBACK_EXCEPTION", message);
        exception.setStackTrace(e.getStackTrace());
        PhantomException phantomException = exceptionManager.create(exception);
        log.error(logTraceInfo(phantomException));
        return phantomException.toString();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        PhantomException exception = new PhantomException(UUIDUtils.getUUID(), appName, ip, "SYSTEM_ERR", e.getMessage());
        exception.setStackTrace(e.getStackTrace());
        PhantomException phantomException = exceptionManager.create(exception);
        log.error(logTraceInfo(phantomException));
        log.error(logTraceInfo(e));
        return phantomException.toString();
    }

    /**
     * KingBoy实现异常栈信息打印，通过查看源码
     * @param e
     * @return
     */
    private String logTraceInfo(Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append(e);
        for (StackTraceElement traceElement : trace) {
            sb.append("\n\tat " + traceElement);
        }
        return sb.toString();
    }

}
