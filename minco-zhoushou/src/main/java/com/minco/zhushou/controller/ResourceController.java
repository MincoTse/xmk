package com.minco.zhushou.controller;

import com.minco.zhushou.param.vo.MenuVO;
import com.minco.zhushou.service.ResourceService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(value = "resource", tags = "资源表（权限）接口")
@RestController
@RequestMapping("resource")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    @GetMapping("menus")
    public List<MenuVO> menus() {
        return resourceService.listMenu();
    }


    @GetMapping("giveResource")
    public List<MenuVO> giveResource() {
        return resourceService.listMenu();
    }


    @GetMapping("addAdmin")
    public List<MenuVO> giveResource(@RequestParam Long userId) {
        List<Long> resourceIds = resourceService.listId();

        return resourceService.listMenu();
    }



}