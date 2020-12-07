package com.minco.zhushou.controller;

import com.minco.zhushou.entity.User;
import com.minco.zhushou.param.request.AuthLoginReq;
import com.minco.zhushou.vo.IndexRspVO;
import com.minco.zhushou.vo.ResourceVO;
import com.minco.zhushou.vo.UserInfoVO;
import com.minco.zhushou.service.BackResourceService;
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

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/18 22:02
 */
@Api(value = "auth", tags = "学员会员接口")
@Controller
@RequestMapping("auth")
public class AuthController {

    @Resource
    private UserService userService;

    @Resource
    private BackResourceService backResourceService;

    @Resource
    private UserPermissionService userPermissionService;


    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("authentication")
    @ResponseBody
    public Result login(@RequestBody AuthLoginReq loginVo) {
        //Assert.isBlank(loginVo.getUsername(), CodeMessageEnum.User.user_name_is_blank);
        //Assert.isBlank(loginVo.getPassword(), CodeMessageEnum.User.user_pwd_is_blank);
        //
        //User user = new User();
        //user.setUserName(loginVo.getUsername());
        //user.setLoginPassword(loginVo.getPassword());
        //
        //User dbUser = userService.getByName(user);
        //Assert.isNull(dbUser, CodeMessageEnum.User.user_not_exist);

        return Result.ok();
    }


    /**
     * 登录成功后跳转到首页
     *
     * @return
     */
    @GetMapping("welcome")
    public String index(Model model) {
        Long userId = 1L;

        User user = userService.getById(userId);

        //获取用户权限
        //List<Long> resourceIds = userPermissionService.listUserResourceIds(userId);
        //List<ResourceVO> resourceVOS = backResourceService.listMenu(resourceIds);
        List<ResourceVO> resourceVOS = backResourceService.listMenu();


        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setId(user.getId());
        userInfoVO.setName(user.getUserName());
        IndexRspVO indexData = new IndexRspVO();
        indexData.setMenus(resourceVOS);
        indexData.setUserInfo(userInfoVO);
        String s = JacksonUtils.toString(indexData);
        model.addAttribute("object", s);
        System.out.println(s);
        return "index";
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("out")
    @ResponseBody
    public Result out() {
        return Result.ok();
    }


}
