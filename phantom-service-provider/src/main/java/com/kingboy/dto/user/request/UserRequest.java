package com.kingboy.dto.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @ApiModelProperty(value = "id", required = true, example = "5")
    private Long id;

    @ApiModelProperty(value = "用户名", example = "kingboy")
    private String username;

    @ApiModelProperty(value = "姓名", example = "金")
    private String name;

    @ApiModelProperty(value = "年龄", example = "24")
    private Integer age;

    @ApiModelProperty(value = "余额", example = "120.00")
    private BigDecimal balance;

}
