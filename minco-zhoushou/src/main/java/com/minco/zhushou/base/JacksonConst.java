package com.minco.zhushou.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.xmk.common.constant.CommonConst;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/12/22 16:06
 */
public class JacksonConst {

    public static ObjectMapper getResponseObjectMapper(){
        Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder = new Jackson2ObjectMapperBuilder();
        //若POJO对象的属性值为null，序列化时不进行显示
        //jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);

        //若POJO对象的属性值为""，序列化时不进行显示
        //jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_EMPTY);

        //DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES相当于配置，JSON串含有未知字段时，反序列化依旧可以成功
        jacksonObjectMapperBuilder.failOnUnknownProperties(false);

        //默认开启，将Date类型序列化为数字时间戳(毫秒表示)关闭 将Date类型序列化为数字时间戳(毫秒表示)
        jacksonObjectMapperBuilder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //针对于Date类型，文本格式化
        jacksonObjectMapperBuilder.simpleDateFormat(CommonConst.SIMPLE_DATE_TIME_FORMAT);


        //默认关闭，即使用BigDecimal.toString()序列化。开启后，使用BigDecimal.toPlainString序列化，不输出科学计数法的值。
        jacksonObjectMapperBuilder.featuresToEnable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

        //支持解析识别注释
        jacksonObjectMapperBuilder.featuresToEnable(JsonParser.Feature.ALLOW_COMMENTS);

        setLongModule(jacksonObjectMapperBuilder);
        setJavaTimeModule(jacksonObjectMapperBuilder);
        ObjectMapper objectMapper = jacksonObjectMapperBuilder.createXmlMapper(false).build();
        return  objectMapper;
    }


    public static ObjectMapper getRedisObjectMapper(){
        Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder = new Jackson2ObjectMapperBuilder();
        //若POJO对象的属性值为null，序列化时不进行显示
        jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);

        //若POJO对象的属性值为""，序列化时不进行显示
        jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_EMPTY);

        //DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES相当于配置，JSON串含有未知字段时，反序列化依旧可以成功
        jacksonObjectMapperBuilder.failOnUnknownProperties(false);

        //默认开启，将Date类型序列化为数字时间戳(毫秒表示)关闭 将Date类型序列化为数字时间戳(毫秒表示)
        jacksonObjectMapperBuilder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //针对于Date类型，文本格式化
        jacksonObjectMapperBuilder.simpleDateFormat(CommonConst.SIMPLE_DATE_TIME_FORMAT);


        //默认关闭，即使用BigDecimal.toString()序列化。开启后，使用BigDecimal.toPlainString序列化，不输出科学计数法的值。
        jacksonObjectMapperBuilder.featuresToEnable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

        setLongModule(jacksonObjectMapperBuilder);
        setJavaTimeModule(jacksonObjectMapperBuilder);
        ObjectMapper objectMapper = jacksonObjectMapperBuilder.createXmlMapper(false).build();
        return  objectMapper;
    }

    private static void setJavaTimeModule(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(CommonConst.SIMPLE_DATE_TIME_FORMAT);
        //针对于JDK新时间类。序列化时带有T的问题，自定义格式化字符串
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));

        jacksonObjectMapperBuilder.modules(javaTimeModule);
    }

    private static void setLongModule(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        jacksonObjectMapperBuilder.modules(simpleModule);
    }


}
