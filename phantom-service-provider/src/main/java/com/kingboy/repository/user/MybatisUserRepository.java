package com.kingboy.repository.user;

import com.kingboy.domain.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/21 下午8:35
 * @desc ${DESCRIPTION}.
 */
@Mapper
public interface MybatisUserRepository {

    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") Long id);

    @Select("select * from user")
    List<User> listUserPage();

    @Insert("insert into `user`(id, username, name, age, balance) "
            + "VALUES (#{id}, #{username}, #{name}, #{age}, #{balance});")
    int save(User userEntity);

}
