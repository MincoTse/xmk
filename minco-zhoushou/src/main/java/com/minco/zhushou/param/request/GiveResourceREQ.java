package com.minco.zhushou.param.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/23 19:13
 */
@Data
public class GiveResourceREQ implements Serializable {

    private List<Long> resourceId;



}
