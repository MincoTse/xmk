package com.minco.zhushou.vo;

import lombok.Data;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/12/5 18:03
 */
@Data
public class UserResourceRspVO {

    private List<Long> userResourceIds;

    private List<ResourceVO> resourceList;

}
