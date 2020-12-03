package com.xmk.common.enums;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/4 22:40
 */

import java.util.HashMap;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author admin
 * @create 2018/11/12 15:37
 */
public class KeyEnumHolder {

    private static HashMap<String, List<EnumItem>> enumVoHashMap = new HashMap<>();

    public static List<EnumItem> get(String key) {
        return enumVoHashMap.get(key);
    }

    public static boolean containsKey(String key) {
        return enumVoHashMap.containsKey(key);
    }

    public static void put(String key, List<EnumItem> dictVOList) {
        enumVoHashMap.put(key, dictVOList);
    }

}
