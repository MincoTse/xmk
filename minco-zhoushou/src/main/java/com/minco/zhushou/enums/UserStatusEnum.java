package com.minco.zhushou.enums;

import com.xmk.common.enums.BusinessEnum;
import lombok.Getter;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/18 22:09
 */
@BusinessEnum
@Getter
public enum UserStatusEnum {

    NORMAL(1),
    DISABLE(2),
    ;


    UserStatusEnum(int status) {
        this.value = status;
    }

    private int value;

    public static String key() {
        return "userStatusEnum";
    }
}
