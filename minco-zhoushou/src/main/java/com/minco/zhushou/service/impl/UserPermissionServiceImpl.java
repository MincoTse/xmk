package com.minco.zhushou.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minco.zhushou.dao.UserPermissionDao;
import com.minco.zhushou.entity.UserPermission;
import com.minco.zhushou.service.UserPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionDao, UserPermission> implements UserPermissionService {

    @Override
    public boolean remove(Long userId) {
        LambdaQueryWrapper<UserPermission> query = Wrappers.<UserPermission>lambdaQuery().eq(UserPermission::getUserId, userId);

        return remove(query);
    }

    @Override
    public boolean saveBatch(Long userId, List<Long> resourceIdList) {
        List<UserPermission> collect = resourceIdList.stream().map(p -> {
            UserPermission userPermission = new UserPermission();
            userPermission.setUserId(userId).setPermissionId(p);
            return userPermission;
        }).collect(Collectors.toList());

        return saveBatch(collect);
    }

    @Override
    public List<Long> listUserResourceIds(Long userId) {
        LambdaQueryWrapper<UserPermission> query = Wrappers.<UserPermission>lambdaQuery()
                .select(UserPermission::getPermissionId)
                .eq(UserPermission::getUserId, userId);

        List<Long> resourceIds = listObjs(query, p -> (Long) p);
        return resourceIds;
    }
}