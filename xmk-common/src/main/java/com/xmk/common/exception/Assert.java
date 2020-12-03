package com.xmk.common.exception;

import cn.hutool.core.util.StrUtil;

import java.util.Objects;
import java.util.function.Function;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/14 20:41
 */
public class Assert {


    public static void notNull(Object object, CodeMessageIdentity codeMessage) {
        notNull(object, codeMessage, null);

    }

    public static void notNull(Object object, CodeMessageIdentity codeMessage, Object... args) {
        isTrue(object, Objects::nonNull, codeMessage, args);
    }


    public static void isNull(Object object, CodeMessageIdentity codeMessage) {
        isNull(object, codeMessage, null);
    }


    public static void isNull(Object object, CodeMessageIdentity codeMessage, Object... args) {
        isTrue(object, Objects::isNull, codeMessage, args);
    }

    private static void isTrue(Object object, Function<Object, Boolean> function, CodeMessageIdentity codeMessage, Object... args) {
        Boolean result = function.apply(object);
        if (!result) {
            return;
        }

        if (Objects.isNull(args)) {
            throw new BaseException(codeMessage);
        } else {
            throw new BaseException(codeMessage.getCode(), codeMessage.getMessage(), args);
        }

    }


    public static void notBlank(String content, CodeMessageIdentity codeMessage) {
        if (StrUtil.isBlank(content)) {
            throw new BaseException(codeMessage);
        }
    }

    public static void notBlank(String content, CodeMessageIdentity codeMessage, Object... args) {
        if (StrUtil.isBlank(content)) {
            throw new BaseException(codeMessage.getCode(), codeMessage.getMessage(), args);
        }
    }

    public static void isBlank(String content, CodeMessageIdentity codeMessage) {
        if (!StrUtil.isBlank(content)) {
            throw new BaseException(codeMessage);
        }
    }

    public static void isBlank(String content, CodeMessageIdentity codeMessage, Object... args) {
        if (!StrUtil.isBlank(content)) {
            throw new BaseException(codeMessage.getCode(), codeMessage.getMessage(), args);
        }
    }

    public static void isTrue(Boolean expression, CodeMessageIdentity codeMessage) {
        if (expression != null && !expression) {
            throw new BaseException(codeMessage);
        }
    }

    public static void isTrue(Boolean expression, CodeMessageIdentity codeMessage, Object... args) {
        if (expression != null && !expression) {
            throw new BaseException(codeMessage.getCode(), codeMessage.getMessage(), args);
        }
    }

    public static void isFalse(Boolean expression, CodeMessageIdentity codeMessage) {
        if (expression != null && expression) {
            throw new BaseException(codeMessage);
        }
    }

    public static void isFalse(Boolean expression, CodeMessageIdentity codeMessage, Object... args) {
        if (expression != null && expression) {
            throw new BaseException(codeMessage.getCode(), codeMessage.getMessage(), args);
        }
    }


}

