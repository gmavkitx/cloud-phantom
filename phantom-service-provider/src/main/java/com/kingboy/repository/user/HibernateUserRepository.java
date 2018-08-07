package com.kingboy.repository.user;

import com.kingboy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/19 下午10:40
 * @desc 用户仓储接口.
 */
@Repository
public interface HibernateUserRepository extends JpaRepository<User, Long> {

}
