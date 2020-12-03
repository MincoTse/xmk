package com.xmk.common.exception;

import java.text.MessageFormat;

/**
 * 异常类
 * <br>
 *
 * @author 明科
 * @create 2020/11/4 22:44
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 7283191803130530250L;

    private Object[] args;
    private String code;
    private String message;

    public BaseException(CodeMessageIdentity codeMessageIdentity) {
        setAttribute(codeMessageIdentity);
    }

    public BaseException(String code, String msg) {
        setAttribute(code, msg, null);
    }

    public BaseException(String code, String msg, Object... args) {
        setAttribute(code, msg, args);
    }


    public String getExceptionMessage() {
        return MessageFormat.format(message, args);
    }

    public String getCode() {
        return code;
    }


    private void setAttribute(CodeMessageIdentity codeMessageIdentity) {
        setAttribute(codeMessageIdentity.getCode(), codeMessageIdentity.getMessage(), null);
    }

    private void setAttribute(String code, String message, Object... args) {
        this.code = CommonCodeMessage.VALIDATION_EXCEPTION.getCode();
        this.message = CommonCodeMessage.VALIDATION_EXCEPTION.getMessage();
        this.args = args;
    }


}
