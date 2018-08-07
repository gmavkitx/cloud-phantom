package com.kingboy.controller.user;

import com.kingboy.common.utils.page.PageParam;
import com.kingboy.common.utils.page.PageResult;
import com.kingboy.service.user.UserService;
import com.kingboy.dto.user.request.UserRequest;
import com.kingboy.dto.user.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/19 下午10:43
 * @desc 用户接口.
 */
@Api(value = "用户操作接口")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;

    @ApiOperation(value = "根据用户ID查找用户，如果用户为null，则抛出自定义异常")
    @GetMapping("/{id:\\d+}")
    public UserResponse getUser(@PathVariable Long id) {
        UserResponse userByIdMybatis = userService.getUserByIdMybatis(id);
        return userService.getUserByIdHibernate(id);
    }

    @ApiOperation(value = "添加或更新一个用户(是否存在ID)")
    @PostMapping
    public String save(@RequestBody UserRequest userRequest) {
        userService.save(userRequest);
        return "success";
    }

    @ApiOperation(value = "分页查询用户-hibernate")
    @GetMapping(value = "/hibernate")
    public PageResult<UserResponse> listUserByHibernate(@ModelAttribute PageParam pageParam) {
        //Hibernate分页查询
        PageResult<UserResponse> userResponsePageResult = userService.listUserByHibernate(pageParam);
        return userResponsePageResult;
    }

    @ApiOperation(value = "分页查询用户-mybatis")
    @GetMapping(value = "/mybatis")
    public PageResult<UserResponse> listUserByMybatis(@ModelAttribute PageParam pageParam) {
        //Mybatis分页查询
        PageResult<UserResponse> userResponsePageResult = userService.listUserByMybatis(pageParam);
        return userResponsePageResult;
    }

    @ApiOperation(value = "抛出系统异常")
    @GetMapping(value = "/test/exception")
    public void testSystemException() {
        int a = 1 / 0;
    }
 }
