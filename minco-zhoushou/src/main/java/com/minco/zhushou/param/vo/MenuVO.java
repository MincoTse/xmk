package com.minco.zhushou.param.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/18 22:23
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MenuVO implements Serializable {

    @ApiModelProperty("菜单key")
    private Long id;

    @ApiModelProperty("菜单名")
    private String label;
    //
    //@ApiModelProperty("菜单是否禁用")
    //private boolean disabled;

    @ApiModelProperty("api 路径")
    private String apiPath;

    @ApiModelProperty("子节点集合")
    private List<MenuVO> children;




}
