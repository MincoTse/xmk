package com.minco.zhushou.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/4 21:22
 */
public class JacksonUtils {

    public static String toString(Object object)  {

        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return result;
    }
}
