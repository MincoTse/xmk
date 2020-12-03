package com.xmk.common.exception;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/4 22:43
 */
public interface CodeMessageIdentity {

    /**
     * 信息对应的code
     * 默认参数错误code
     *
     * @return
     */
    String getCode();


    /**
     * 提示信息
     *
     * @return
     */
    String getMessage();
}
