package com.minco.zhushou.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.minco.zhushou.entity.User;
import com.minco.zhushou.param.query.BackUserQuery;
import com.minco.zhushou.service.BackResourceService;
import com.minco.zhushou.service.UserPermissionService;
import com.minco.zhushou.service.UserService;
import com.minco.zhushou.utils.JacksonUtils;
import com.minco.zhushou.vo.CheckBoxVO;
import com.minco.zhushou.vo.ResourceVO;
import com.minco.zhushou.vo.UserResourceRspVO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value = "user", tags = "用户接口")
@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService service;

    @Resource
    private UserPermissionService userPermissionService;

    @Resource
    private BackResourceService backResourceService;


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
     * @return
     */
    @GetMapping("show")
    public String getInfo(Model model) {
        Long userId = 1L;
        User user = service.getById(userId);
        List<Long> userResourceIds = userPermissionService.listUserResourceIds(userId);
        List<ResourceVO> resourceVOS = backResourceService.listResourceVO();

        List<CheckBoxVO> list = new ArrayList<>();
        for (ResourceVO resourceVO : resourceVOS) {
            CheckBoxVO vo = getBoxVO(resourceVO);
            list.add(vo);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userResourceIds", userResourceIds);
        jsonObject.put("options", list);

        model.addAttribute("object", JacksonUtils.toString(jsonObject));
        return "config/my";
    }

    @GetMapping("update")
    public String getResource(Model model) {
        Long userId = 1L;
        User user = service.getById(userId);
        List<Long> userResourceIds = userPermissionService.listUserResourceIds(userId);
        List<ResourceVO> resourceVOS = backResourceService.listResourceVO();
        UserResourceRspVO rspVO = new UserResourceRspVO();
        rspVO.setResourceList(resourceVOS);
        rspVO.setUserResourceIds(userResourceIds);

        model.addAttribute("object", JacksonUtils.toString(rspVO));
        return "config/update";
    }

    private CheckBoxVO getBoxVO(ResourceVO resourceVO) {
        CheckBoxVO vo = new CheckBoxVO();
        vo.setLabel(resourceVO.getLabel());
        vo.setValue(resourceVO.getId().toString());
        List<CheckBoxVO> checkBoxVOS = new ArrayList<>();
        if (CollUtil.isNotEmpty(resourceVO.getChildren())) {
            for (ResourceVO child : resourceVO.getChildren()) {
                CheckBoxVO boxVO = getBoxVO(child);
                checkBoxVOS.add(boxVO);
            }
        }
        vo.setChildren(checkBoxVOS);
        return vo;
    }


}