package com.minco.zhushou.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 适用多选框
 * <br>
 *
 * @author 明科
 * @create 2020/11/28 20:58
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckBoxVO {

    @ApiModelProperty("标识")
    private String value;

    @ApiModelProperty("名称")
    private String label;

    @ApiModelProperty("子项")
    List<CheckBoxVO> children;
}
