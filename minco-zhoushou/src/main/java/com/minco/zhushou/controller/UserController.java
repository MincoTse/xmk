package com.minco.zhushou.controller;

import com.minco.zhushou.entity.User;
import com.minco.zhushou.entity.UserPermission;
import com.minco.zhushou.param.query.BackUserQuery;
import com.minco.zhushou.service.ResourceService;
import com.minco.zhushou.service.UserPermissionService;
import com.minco.zhushou.service.UserService;
import com.minco.zhushou.utils.JacksonUtils;
import com.xmk.common.base.Result;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(value = "user", tags = "接口")
@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService service;

    @Resource
    private UserPermissionService userPermissionService;

    @Resource
    private ResourceService resourceService;


    @GetMapping("backUserList")
    public String aaa(Model model) {

        List<User> list = service.list();
        String s = JacksonUtils.toString(list);
        model.addAttribute("object", s);

        return "config/backUserList";
    }

    @GetMapping("init")
    public String init(Map<String, Object> map) {
        String result = JacksonUtils.toString(service.init());
        map.put("object", result);
        return "config/backUserList";
    }

    @PostMapping("search")
    @ResponseBody
    public Result getListPage(@RequestBody BackUserQuery backUserQuery) {
        return Result.ok(service.getListWithPage(backUserQuery));
    }


    /**
     * 获取用户信息以及权限信息
     *
     * @param backUserQuery
     * @return
     */
    @PostMapping("getInfo")
    @ResponseBody
    public Result getInfo(@RequestBody BackUserQuery backUserQuery) {

        return Result.ok();
    }

    @GetMapping("getResource")
    public Result getResource() {
        Long userId = 1L;
        User user = service.getById(userId);
        List<Long> resourceIds = userPermissionService.listUserResourceIds(userId);



        return Result.ok();
    }


}