package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/12/25 20:43
 */
@Controller
public class DemoController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserDao userDao;


    @GetMapping("hello")
    @ResponseBody
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("data")
    @ResponseBody
    public List<User> data(){

        List<User> users = userDao.selectList(null);
        return users;
    }

    @GetMapping("redis")
    @ResponseBody
    public String redis(){

        String aaa = stringRedisTemplate.opsForValue().get("aaa");

        return aaa;
    }

    @GetMapping("index")
    public String index(){
        return "content1";
    }
}
