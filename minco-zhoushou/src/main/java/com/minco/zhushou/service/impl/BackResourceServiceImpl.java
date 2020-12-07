package com.minco.zhushou.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minco.zhushou.dao.BackResourceDao;
import com.minco.zhushou.entity.BackResource;
import com.minco.zhushou.service.BackResourceService;
import com.minco.zhushou.vo.ResourceVO;
import com.xmk.common.constant.NumberConst;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class BackResourceServiceImpl extends ServiceImpl<BackResourceDao, BackResource> implements BackResourceService {


    @Override
    public List<Long> listId() {
        List<Long> idList = list().stream().map(p -> p.getId()).collect(Collectors.toList());

        return idList;
    }

    @Override
    public List<ResourceVO> listResourceVO() {
        List<BackResource> list = list();
        List<ResourceVO> resourceVOS = getMenus(list);

        return resourceVOS;
    }

    @Override
    public List<ResourceVO> listMenu() {
        LambdaQueryWrapper<BackResource> queryWrapper = Wrappers.<BackResource>lambdaQuery()
                .in(BackResource::getResourceType, Arrays.asList(NumberConst.ONE,NumberConst.TWO));

        List<BackResource> list = list(queryWrapper);
        List<ResourceVO> resourceVOS = getMenus(list);
        return resourceVOS;
    }

    @Override
    public List<ResourceVO> listMenu(List<Long> resourceIds) {
        LambdaQueryWrapper<BackResource> queryWrapper = Wrappers.<BackResource>lambdaQuery()
                .in(BackResource::getResourceType, Arrays.asList(NumberConst.ONE,NumberConst.TWO))
                .in(BackResource::getId,resourceIds);

        List<BackResource> backResources = list(queryWrapper);
        List<ResourceVO> resourceVOS = getMenus(backResources);

        return resourceVOS;
    }

    private List<ResourceVO> getMenus(List<BackResource> backResourceList) {
        List<BackResource> topBackResourceList = new ArrayList<>();
        List<BackResource> sonBackResourceList = new ArrayList<>();
        for (BackResource backResource : backResourceList) {
            if (NumberConst.ZERO_INT == backResource.getParentId()) {
                topBackResourceList.add(backResource);
            } else {
                sonBackResourceList.add(backResource);
            }
        }

        Map<Long/*parentId*/, List<BackResource>> parentIdMap = sonBackResourceList.stream().filter(p -> NumberConst.ZERO_INT != p.getParentId())
                .collect(Collectors.groupingBy(BackResource::getParentId));


        List<ResourceVO> result = new ArrayList<>();
        for (BackResource backResource : topBackResourceList) {
            ResourceVO menu = childrenMenu(backResource, parentIdMap);
            result.add(menu);
        }
        return result;
    }


    private ResourceVO childrenMenu(BackResource current, Map<Long, List<BackResource>> parentIdMap) {
        //判断是否有子菜单
        Long parentId = current.getId();
        List<BackResource> backResources = parentIdMap.get(parentId);
        List<ResourceVO> child = new ArrayList<>();
        if (CollUtil.isNotEmpty(backResources)) {
            for (BackResource backResource : backResources) {
                ResourceVO menu = childrenMenu(backResource, parentIdMap);
                child.add(menu);
            }
        }

        //组装菜单
        ResourceVO resourceVO = convert(current);
        resourceVO.setChildren(child);
        return resourceVO;
    }



    private ResourceVO convert(BackResource backResource) {
        return ResourceVO.builder().id(backResource.getId())
                .label(backResource.getResourceName())
                .apiPath(backResource.getResourceUrl())
                .children(CollUtil.newArrayList())
                .build();
    }


}