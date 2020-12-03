package com.minco.zhushou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minco.zhushou.entity.User;
import com.minco.zhushou.param.query.BackUserQuery;
import com.minco.zhushou.base.MyPage;


/**
 * data service layer
 *
 * @author minco
 * @since 2020-11-19 20:16:51
 */
public interface UserService extends IService<User> {


    User getByName(User user);

    /**
     * pc端后台显示
     *
     * @return
     */
    MyPage init();

    /**
     * 查询
     *
     * @param backUserQuery
     * @return
     */
    MyPage getListWithPage(BackUserQuery backUserQuery);
}