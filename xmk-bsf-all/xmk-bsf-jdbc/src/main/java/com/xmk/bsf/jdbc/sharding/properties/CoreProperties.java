package com.xmk.bsf.jdbc.sharding.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * sharding公用配置
 * <br>
 *
 * @author 明科
 * @create 2020/5/11 15:22
 */
@Data
@ConfigurationProperties(prefix = "qyx.core.test")
public class CoreProperties {

    private String name;

    private String age;



}
