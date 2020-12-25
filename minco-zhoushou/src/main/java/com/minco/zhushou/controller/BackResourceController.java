package com.minco.zhushou.controller;

import com.alibaba.fastjson.JSONObject;
import com.minco.zhushou.entity.BackResource;
import com.minco.zhushou.param.request.BackResourceReq;
import com.minco.zhushou.service.BackResourceService;
import com.minco.zhushou.vo.ResourceVO;
import io.swagger.annotations.Api;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Api(value = "resource", tags = "后台资源表（权限）接口")
@RestController
@RequestMapping("resource")
public class BackResourceController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private BackResourceService backResourceService;


    @Cacheable(key = "", value = "listResource")
    @PostMapping("list")
    public List<BackResource> listResource(@RequestBody BackResourceReq req) {

        return backResourceService.list();
    }

    @GetMapping("testLocalDate")
    public JSONObject testLocalDate() {
        log.debug("log debug {}", LocalDateTime.now());
        log.info("log info {}", LocalDateTime.now());
        log.error("log error {}", LocalDateTime.now());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", new Aaa());
        jsonObject.put("2", backResourceService.list());

        return jsonObject;
    }

    @GetMapping("test")
    public String test() {
        stringRedisTemplate.opsForValue().set("aa", "asdasdas");
        String minco = stringRedisTemplate.opsForValue().get("aa");

        return minco;
    }

    @GetMapping("hello")
    public String hello() {
        return "hello world!";
    }

    @GetMapping("addAdmin")
    public List<ResourceVO> giveResource(@RequestParam Long userId) {
        //获取权限id列表
        List<Long> resourceIds = backResourceService.listId();
        //权限id 付给用户

        return backResourceService.listMenu();
    }

    @Data
    static class Aaa {
        private String name = "祥涵";
        private LocalDateTime localDateTime = LocalDateTime.now();
        private LocalDate localDate = LocalDate.now();
        private LocalTime localTime = LocalTime.now();
    }


}