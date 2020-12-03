package com.minco.zhushou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minco.zhushou.entity.Resource;
import com.minco.zhushou.param.vo.MenuVO;

import java.util.List;


/**
 * 资源表（权限）
 * data service layer
 *
 * @author minco
 * @since 2020-11-19 20:17:38
 */
public interface ResourceService extends IService<Resource> {


    /**
     * 获取所有权限集合
     * @return
     */
    List<Long> listId();


    List<MenuVO> listMenu();


    List<MenuVO> listMenu(List<Long> resourceIds);











}