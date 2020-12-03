package com.minco.zhushou.param.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 *  运营后台账号
 * <br>
 *
 * @author 明科
 * @create 2020/11/28 21:00
 */
@Data
public class AccountInfoVO {

    private Long id;

    private String userName;

    private String phone;

    @ApiModelProperty("资源组列表")
    List<CheckBoxVO> checkBoxGroupList;

}
