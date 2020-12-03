package com.minco.zhushou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minco.zhushou.entity.UserPermission;

import java.util.List;


/**
 * 用户权限关系表
 * data service layer
 *
 * @author minco
 * @since 2020-11-19 20:17:19
 */
public interface UserPermissionService extends IService<UserPermission> {

    boolean remove(Long userId);

    boolean saveBatch(Long userId, List<Long> resourceIdList);

    List<Long> listUserResourceIds(Long userId);


}