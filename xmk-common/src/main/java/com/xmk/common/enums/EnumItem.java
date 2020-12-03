package com.xmk.common.enums;

import lombok.Data;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/23 23:05
 */
@Data
public class EnumItem {

    private String type;
    private String text;


    public EnumItem(String text, String value) {
        this.text = text;
        this.type = value;
    }
}
