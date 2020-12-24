package com.minco.zhushou.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minco.zhushou.base.JacksonConst;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/4 21:22
 */
public class JacksonUtils{

    private static ObjectMapper responseObjectMapper = JacksonConst.getResponseObjectMapper();

    public static String toString(Object object) {
        String result = null;
        try {
            result = responseObjectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
