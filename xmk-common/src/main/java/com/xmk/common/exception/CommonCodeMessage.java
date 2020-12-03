package com.xmk.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/4 22:54
 */
@Getter
@AllArgsConstructor
public enum CommonCodeMessage implements CodeMessageIdentity {


    SUCCESS("S0000", "SUCCESS"),

    DEFAULT_EXCEPTION("S0001", "系统开了个小差，请稍后再试！"),

    SIGN_ERROR("S0002", "签名错误"),

    VALIDATION_EXCEPTION("S0003", "参数错误，非空为空！"),

    BUSINESS_EXCEPTION("B0001", "业务异常!"),
    ;


    private String code;
    private String message;


}
