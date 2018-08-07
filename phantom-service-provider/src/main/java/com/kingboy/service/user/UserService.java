package com.kingboy.service.user;

import com.kingboy.common.utils.page.PageParam;
import com.kingboy.common.utils.page.PageResult;
import com.kingboy.dto.user.request.UserRequest;
import com.kingboy.dto.user.response.UserResponse;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/19 下午10:44
 * @desc 用户服务接口.
 */
public interface UserService {

    UserResponse getUserByIdHibernate(Long id);

    UserResponse getUserByIdMybatis(Long id);

    void save(UserRequest userRequest);

    PageResult<UserResponse> listUserByHibernate(PageParam pageParam);

    PageResult<UserResponse> listUserByMybatis(PageParam pageParam);
}
