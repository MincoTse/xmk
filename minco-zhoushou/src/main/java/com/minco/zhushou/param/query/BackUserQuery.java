package com.minco.zhushou.param.query;


import com.minco.zhushou.entity.User;
import com.minco.zhushou.base.MyPage;
import lombok.Data;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/18 22:16
 */
@Data
public class BackUserQuery extends MyPage<User> {

    private Long id;

    private String phone;

    private String name;

    private Integer status;

}
