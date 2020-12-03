package com.xmk.common.enums;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/23 23:05
 */
@Accessors(chain = true)
@Data
public class EnumVO {

    private String key;
    private String description;
    private List<EnumItem> enumItemList = new ArrayList<>();

    public void addEnumItem(String name, String type) {
        this.enumItemList.add(new EnumItem(name, type));
    }

}
