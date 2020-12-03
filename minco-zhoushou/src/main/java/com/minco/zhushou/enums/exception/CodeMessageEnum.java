package com.minco.zhushou.enums.exception;

import com.xmk.common.exception.CodeMessageIdentity;
import lombok.Getter;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/18 22:10
 */
public interface CodeMessageEnum {

    @Getter
    enum User implements CodeMessageIdentity {

        user_name_is_blank("请输入账号！"),
        user_pwd_is_blank("请输入密码！"),
        user_not_exist("账号不存在！"),
        pwd_error("请输入正确的密码！"),
        user_cancel("账户已注销！"),
        un_config_permission("该账户权限未配置，请联系管理员配置！"),
        login_error("登录失败!"),
        ;

        private String code;
        private String message;

        User(String message) {
            this.message = message;
        }


    }
}
