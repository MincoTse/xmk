package com.xmk.bsf.jdbc.sharding.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/5/7 14:29
 */
@Data
@PropertySource("classpath:config/application.yml")
@ConfigurationProperties(prefix = "qyx.datasource")
public class DataSourceProperties {

    private String driverClassName;

    private String url;

    private String slave;

    private String username;

    private String password;

    private Boolean sqlShow;

    private Boolean sqlSimple;



    private List<String> broadcastTables;

    private List<String> bindTables;


}
