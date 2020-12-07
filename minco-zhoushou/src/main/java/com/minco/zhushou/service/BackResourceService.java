package com.minco.zhushou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minco.zhushou.entity.BackResource;
import com.minco.zhushou.vo.ResourceVO;

import java.util.List;


/**
 * 资源表（权限）
 * data service layer
 *
 * @author minco
 * @since 2020-11-19 20:17:38
 */
public interface BackResourceService extends IService<BackResource> {


    /**
     * 获取所有权限集合
     * @return
     */
    List<Long> listId();

    List<ResourceVO> listResourceVO();

    List<ResourceVO> listMenu();


    List<ResourceVO> listMenu(List<Long> resourceIds);











}