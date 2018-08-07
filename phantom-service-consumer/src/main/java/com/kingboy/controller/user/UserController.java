package com.kingboy.controller.user;

import com.kingboy.common.tools.exception.ExceptionManager;
import com.kingboy.common.utils.result.ApiResult;
import com.kingboy.service.user.UserService;
import com.kingboy.dto.user.request.UserRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/20 上午1:37
 * @desc 用户远程调用示例.
 */
@Api(value = "用户操作接口")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    ExceptionManager exceptionManager;

    @ApiOperation(value = "根据用户ID查找用户，当用户为空时，下游抛自定义异常走回掉方法。并且回掉方法中抛出了自定义异常")
    @GetMapping(value = "/{id:\\d+}")
    public ApiResult getUserById(@PathVariable Long id) {
        return ApiResult.success(userService.getUser(id));
    }

    @ApiOperation(value = "添加一个用户")
    @PostMapping
    public ApiResult saveUser(@RequestBody UserRequest userRequest) {
        String isSuccess = userService.save(userRequest);
        if (isSuccess.equals("fail")) {
            return ApiResult.error("-1", "添加用户失败");
        }
        return ApiResult.success("success");
    }

    @ApiOperation(value = "分页查询用户")
    @GetMapping(value = "/hibernate")
    public ApiResult listUserByHibernate(@RequestParam Integer page, @RequestParam Integer size) {
        //注意此处不能pageParam进行封装，因为pageParam中的p进行了特殊处理
        return ApiResult.success(userService.listUserByHibernate(page, size));
    }

    @ApiOperation(value = "分页查询用户")
    @GetMapping(value = "/mybatis")
    public ApiResult listUserByMybatis(@RequestParam Integer page, @RequestParam Integer size) {
        //注意此处不能pageParam进行封装，因为pageParam中的p进行了特殊处理
        return ApiResult.success(userService.listUserByMybatis(page, size));
    }

    @ApiOperation(value = "测试下游服务抛出的系统异常，并在会掉方法中抛出了系统异常")
    @GetMapping(value = "/test/exception1")
    public ApiResult exceptionTest1() {
        userService.testSystemException();
        return ApiResult.success("你永远看不到我");
    }

    @ApiOperation(value = "测试自服务抛出的异常(自定义/系统=1)")
    @GetMapping(value = "/test/exception2/{type}")
    public ApiResult exceptionTest2(@PathVariable Integer type) {
        if (type.equals(1)) {
            int a = 1 / 0;
        } else {
            throw exceptionManager.create("CONSU_0001");
        }
        return ApiResult.success("你永远看不到我");
    }

}
