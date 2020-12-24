package com.minco.zhushou.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minco.zhushou.base.JacksonConst;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * com.fasterxml.jackson.databind.SerializationFeature
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/12/8 15:55
 */
@Configuration
public class JacksonConfig {


    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {

        return JacksonConst.getResponseObjectMapper();
    }


}
