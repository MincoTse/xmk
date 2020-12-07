package com.minco.zhushou.vo;

import lombok.Data;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/18 22:29
 */
@Data
public class IndexRspVO {

    private UserInfoVO userInfo;

    private List<ResourceVO> menus;


}
