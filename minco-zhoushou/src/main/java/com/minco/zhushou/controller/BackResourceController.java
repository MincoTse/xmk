package com.minco.zhushou.controller;

import com.minco.zhushou.service.BackResourceService;
import com.minco.zhushou.vo.ResourceVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "resource", tags = "后台资源表（权限）接口")
@RestController
@RequestMapping("resource")
public class BackResourceController {

    @Resource
    private BackResourceService backResourceService;

    @GetMapping("list")
    public List<ResourceVO> listResource() {
        return backResourceService.listMenu();
    }


    @GetMapping("addAdmin")
    public List<ResourceVO> giveResource(@RequestParam Long userId) {
        //获取权限id列表
        List<Long> resourceIds = backResourceService.listId();
        //权限id 付给用户

        return backResourceService.listMenu();
    }



}