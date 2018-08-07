package com.kingboy.filter;

import com.kingboy.common.tools.exception.ExceptionManager;
import com.kingboy.common.tools.exception.PhantomException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User kingboy - KingBoyWorld@163.com
 * Date 2018/3/14 19:36
 * Desc 前置过滤器
 */
@Component
public class PreFilter extends ZuulFilter{

    @Resource
    ExceptionManager exceptionManager;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        String path = RequestContext.getCurrentContext().getRequest().getServletPath();
        System.out.println(path);

        if (path.contains("/system/exception")) {
            //演示系统异常
            int i = 1 / 0;
            return null;
        }

        if (path.contains("/define/exception")) {
            //演示自定义异常
            throw exceptionManager.createByCode("ZUUL_0001");
        }
        return null;
    }

}
