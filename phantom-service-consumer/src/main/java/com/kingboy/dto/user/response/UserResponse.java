package com.kingboy.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/19 下午11:13
 * @desc 用户返回体.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String username;

    private String name;

    private Integer age;

    private BigDecimal balance;

}
