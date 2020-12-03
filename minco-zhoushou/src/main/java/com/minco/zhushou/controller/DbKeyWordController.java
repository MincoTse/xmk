package com.minco.zhushou.controller;

import com.minco.zhushou.service.DbKeyWordService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "dbKeyWord", tags = "mysql关键字保修自表接口")
@RestController
@RequestMapping("dbKeyWord")
public class DbKeyWordController {

    @Resource
    private DbKeyWordService dbKeyWordService;


}