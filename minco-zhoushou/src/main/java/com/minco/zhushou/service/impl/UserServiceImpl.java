package com.minco.zhushou.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minco.zhushou.dao.UserDao;
import com.minco.zhushou.entity.User;
import com.minco.zhushou.enums.UserStatusEnum;
import com.minco.zhushou.param.query.BackUserQuery;
import com.minco.zhushou.service.UserService;
import com.minco.zhushou.base.MyPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {


    @Override
    public User getByName(User user) {
        LambdaQueryWrapper<User> query = Wrappers.<User>lambdaQuery()
                .eq(User::getUserName, user.getUserName())
                .eq(User::getLoginPassword, user.getLoginPassword());

        return getOne(query);
    }

    @Override
    public MyPage init() {
        MyPage page = getListWithPage(new BackUserQuery());
        page.putEnums(UserStatusEnum.key());

        return page;
    }

    @Override
    public MyPage getListWithPage(BackUserQuery backUserQuery) {
        log.debug("getListWithPage backUserQuery={}", backUserQuery);
        LambdaQueryWrapper<User> query = Wrappers.<User>lambdaQuery();
        if (backUserQuery.getStatus() != null) {
            query.eq(User::getUserStatus, backUserQuery.getStatus());
        }
        if (backUserQuery.getId() != null) {
            query.eq(User::getId, backUserQuery.getId());
        }
        MyPage<User> page = page(backUserQuery, query);

        return page;
    }


}