package com.minco.zhushou.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minco.zhushou.dao.ResourceDao;
import com.minco.zhushou.entity.Resource;
import com.minco.zhushou.param.vo.MenuVO;
import com.minco.zhushou.service.ResourceService;
import com.xmk.common.constant.NumberConst;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {


    @Override
    public List<Long> listId() {
        List<Long> idList = list().stream().map(p -> p.getId()).collect(Collectors.toList());

        return idList;
    }

    @Override
    public List<MenuVO> listMenu() {
        LambdaQueryWrapper<Resource> queryWrapper = Wrappers.<Resource>lambdaQuery()
                .in(Resource::getResourceType, Arrays.asList(NumberConst.ONE,NumberConst.TWO));

        List<Resource> list = list(queryWrapper);
        List<MenuVO> menuVOS = getMenus(list);
        return menuVOS;
    }

    @Override
    public List<MenuVO> listMenu(List<Long> resourceIds) {
        LambdaQueryWrapper<Resource> queryWrapper = Wrappers.<Resource>lambdaQuery()
                .in(Resource::getResourceType, Arrays.asList(NumberConst.ONE,NumberConst.TWO))
                .in(Resource::getId,resourceIds);

        List<Resource> resources = list(queryWrapper);
        List<MenuVO> menuVOS = getMenus(resources);

        return menuVOS;
    }

    private List<MenuVO> getMenus(List<Resource> resourceList) {
        List<Resource> topResourceList = new ArrayList<>();
        List<Resource> sonResourceList = new ArrayList<>();
        for (Resource resource : resourceList) {
            if (NumberConst.ZERO_INT == resource.getParentId()) {
                topResourceList.add(resource);
            } else {
                sonResourceList.add(resource);
            }
        }

        Map<Long/*parentId*/, List<Resource>> parentIdMap = sonResourceList.stream().filter(p -> NumberConst.ZERO_INT != p.getParentId())
                .collect(Collectors.groupingBy(Resource::getParentId));


        List<MenuVO> result = new ArrayList<>();
        for (Resource resource : topResourceList) {
            MenuVO menu = childrenMenu(resource, parentIdMap);
            result.add(menu);
        }
        return result;
    }


    private MenuVO childrenMenu(Resource current, Map<Long, List<Resource>> parentIdMap) {
        //判断是否有子菜单
        Long parentId = current.getId();
        List<Resource> resources = parentIdMap.get(parentId);
        List<MenuVO> child = new ArrayList<>();
        if (CollUtil.isNotEmpty(resources)) {
            for (Resource resource : resources) {
                MenuVO menu = childrenMenu(resource, parentIdMap);
                child.add(menu);
            }
        }

        //组装菜单
        MenuVO menuVO = convert(current);
        menuVO.setChildren(child);
        return menuVO;
    }



    private MenuVO convert(Resource resource) {
        return MenuVO.builder().id(resource.getId())
                .label(resource.getResourceName())
                .apiPath(resource.getResourceUrl())
                .children(CollUtil.newArrayList())
                .build();
    }


}