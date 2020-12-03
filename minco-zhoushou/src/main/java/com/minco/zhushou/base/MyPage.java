package com.minco.zhushou.base;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmk.common.constant.NumberConst;
import com.xmk.common.enums.EnumItem;
import com.xmk.common.enums.KeyEnumHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/4 21:42
 */
public class MyPage<T> extends Page<T> {

    private Map<String, List<EnumItem>> enums;

    public MyPage() {
        super(1, NumberConst.TEN_INT);
    }


    public MyPage<T> putEnums(String key) {
        if (StrUtil.isNotBlank(key)) {
            if (enums == null) {
                enums = new HashMap<>(NumberConst.THREE_INT);
            }
            enums.put(key, KeyEnumHolder.get(key));
        }
        return this;
    }


    public Map<String, List<EnumItem>> getEnums() {
        return this.enums;
    }
}
