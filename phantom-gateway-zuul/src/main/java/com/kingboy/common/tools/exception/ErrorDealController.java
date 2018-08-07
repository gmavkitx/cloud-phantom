package com.kingboy.common.tools.exception;

import com.kingboy.common.tools.exception.ExceptionManager;
import com.kingboy.common.tools.exception.PhantomException;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * User kingboy - KingBoyWorld@163.com
 * Date 2018/3/14 21:44
 * Desc
 */
@RestController
public class ErrorDealController implements ErrorController{

    @Value("${error.path:/error}")
    private String errorPath;

    @Resource
    ExceptionManager exceptionManager;

    @GetMapping(value = "${error.path:/error}")
    public String error() {

        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable cause = ctx.getThrowable().getCause();

        if (cause instanceof PhantomException) {
            return cause.toString();
        }
        return exceptionManager.createByMessage(cause.getMessage()).toString();
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
