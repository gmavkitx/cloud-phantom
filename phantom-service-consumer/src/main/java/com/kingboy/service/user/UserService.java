package com.kingboy.service.user;

import com.kingboy.common.tools.exception.ExceptionManager;
import com.kingboy.common.tools.exception.PhantomException;
import com.kingboy.common.utils.page.PageResult;
import com.kingboy.dto.user.request.UserRequest;
import com.kingboy.dto.user.response.UserResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/19 下午10:43
 * @desc 用户接口调用.
 */
@FeignClient(name = "phantom-service-provider", fallbackFactory = UserService.UserServiceFallbackFactory.class)
public interface UserService {

    @GetMapping(value = "/user/{id}")
    UserResponse getUser(@PathVariable("id") Long id);

    @PostMapping(value = "/user")
    String save(@RequestBody UserRequest userRequest);

    @GetMapping(value = "/user/hibernate")
    PageResult<UserResponse> listUserByHibernate(@RequestParam("page") int page, @RequestParam("size") int size);

    @GetMapping(value = "/user/mybatis")
    PageResult<UserResponse> listUserByMybatis(@RequestParam("page") int page, @RequestParam("size") int size);

    @GetMapping(value = "user/test/exception")
    void testSystemException();

    /**
     * 回退工厂
     */
    @Log4j
    @Component
    class UserServiceFallbackFactory implements FallbackFactory<UserService> {

        @Resource
        ExceptionManager exceptionManager;

        @Override
        public UserService create(Throwable cause) {
            return new UserService() {
                //测试fallback中抛出的系统异常
                @Override
                public UserResponse getUser(@PathVariable("id") Long id) {
                    //实验：在fallback中返回下游服务抛的异常
                    if (id == 66) {
                        throw (PhantomException) cause;
                    }
                    //实验：在fallback中抛出新的异常
                    if (id == 88) {
                        throw exceptionManager.create("CONSU_0002");
                    }
                    return new UserResponse(-1L, "default", "default", -1, BigDecimal.valueOf(0.00));
                }

                @Override
                public String save(@RequestBody UserRequest userRequest) {
                    return "fail";
                }

                @Override
                public PageResult<UserResponse> listUserByMybatis(@RequestParam("page") int page, @RequestParam("size") int size) {
                    return new PageResult<>(0, 0, null);
                }

                @Override
                public PageResult<UserResponse> listUserByHibernate(@RequestParam("p") int p, @RequestParam("c") int c) {
                    return new PageResult<>(0, 0, null);
                }

                //测试fallback中抛出系统异常
                @Override
                public void testSystemException() {
                    int a = 1 / 0;
                }
            };
        }
    }

}
