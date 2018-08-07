package com.kingboy.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.kingboy.common.tools.exception.ExceptionManager;
import com.kingboy.common.utils.mapper.MapperUtils;
import com.kingboy.common.utils.page.PageParam;
import com.kingboy.common.utils.page.PageResult;
import com.kingboy.common.utils.page.PageResultFactory;
import com.kingboy.repository.user.HibernateUserRepository;
import com.kingboy.repository.user.MybatisUserRepository;
import com.kingboy.domain.user.User;
import com.kingboy.service.user.UserService;
import com.kingboy.dto.user.request.UserRequest;
import com.kingboy.dto.user.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/19 下午10:44
 * @desc 用户服务实现.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    PageResultFactory pageResultFactory;

    @Resource
    HibernateUserRepository hibernateUserRepository;

    @Resource
    MybatisUserRepository mybatisUserRepository;

    @Resource
    ExceptionManager exceptionManager;

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public UserResponse getUserByIdHibernate(Long id) {
        User user = hibernateUserRepository.findOne(id);
        if (Objects.isNull(user)) {
            throw exceptionManager.create("PROV_0001");
        }
        return MapperUtils.mapperBean(user, UserResponse.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public UserResponse getUserByIdMybatis(Long id) {
        User user = mybatisUserRepository.getUserById(id);
        if (Objects.isNull(user)) {
            throw exceptionManager.create("PROV_0001");
        }
        return MapperUtils.mapperBean(user, UserResponse.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserRequest userRequest) {
        User userEntity = MapperUtils.mapperBean(userRequest, User.class);
        hibernateUserRepository.save(userEntity);
    }

    @Override
    public PageResult<UserResponse> listUserByHibernate(PageParam pageParam) {
        //调用jpa查询数据
        Page<User> page = hibernateUserRepository.findAll(new PageRequest(pageParam.getHibernatePage(),
                pageParam.getSize()));

        //获取结果的页数和总条数，并转换结果
        PageResult<UserResponse> pageResult = pageResultFactory.createAndConvert(pageParam.getPage(),
                page.getTotalElements(),
                page.getContent(),
                UserResponse.class);

        return pageResult;
    }

    @Override
    public PageResult<UserResponse> listUserByMybatis(PageParam pageParam) {
        //设置分页并查询
        com.github.pagehelper.Page<UserResponse> results = PageHelper.startPage(pageParam.getMybatisPage(), pageParam.getSize());
        List<User> userEntities = mybatisUserRepository.listUserPage();

        //获取结果的页数和总条数，并转换结果
        PageResult<UserResponse> pageResult = pageResultFactory.createAndConvert(pageParam.getPage(),
                results.getTotal(), userEntities, UserResponse.class);

        return pageResult;
    }


}
