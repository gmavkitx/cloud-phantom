package com.kingboy.domain.user;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/19 下午10:48
 * @desc 用户实体.
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private BigDecimal balance;

}
