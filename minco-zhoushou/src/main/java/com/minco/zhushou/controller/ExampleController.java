package com.minco.zhushou.controller;

import com.minco.zhushou.properties.MincoTestProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/26 22:16
 */
@RequestMapping("example")
@Controller
public class ExampleController {

    @Resource
    private MincoTestProperties mincoTestProperties;

    /**
     * 示例用户列表不和数据打交道
     * @return
     */
    @GetMapping("user")
    public String userList(){
      return "aa/userList";
    }


    @GetMapping("content1")
    public String content1(Model model) {
        model.addAttribute("title","content1");
        return "aa/content1";
    }

    @GetMapping("content2")
    public String content2(Model model) {
        model.addAttribute("title","content2");
        return "aa/content2";
    }

    @GetMapping("content3")
    public String content3(Model model) {
        model.addAttribute("title","content3");
        HashMap<String, Integer> map = mincoTestProperties.getMap();
        return "aa/content3";
    }

}
