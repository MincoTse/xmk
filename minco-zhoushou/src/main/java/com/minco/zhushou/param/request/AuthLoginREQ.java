package com.minco.zhushou.param.request;

import lombok.Data;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/18 22:07
 */
@Data
public class AuthLoginREQ {
    private String username;
    private String password;
    private String code;

}
