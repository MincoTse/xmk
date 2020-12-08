package com.minco.zhushou.param.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/12/8 17:08
 */
@Data
public class BackResourceReq {

    private String userName;

    private Long id;

    private String name;

    private LocalDateTime localDateTime;

    private LocalDate localDate;

    private LocalTime localTime;

    private Date date;

    private List<String> list;

    private HashMap<String,Integer> map;


}
