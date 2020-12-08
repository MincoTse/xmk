package com.minco.zhushou.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/12/8 9:22
 */
@Data
@Component
@ConfigurationProperties(prefix = "minco.test")
public class MincoTestProperties {

    private HashMap<String, Integer> map;
}
