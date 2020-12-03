package com.minco.zhushou.entity;

import com.xmk.common.base.BaseEntity;
import com.xmk.common.base.DBaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


/**
 * 实体类
 *
 * @author minco
 * @since 2020-11-19 20:16:50
 */
@Data
@Accessors(chain = true)
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 452838350707061117L;

    /**
     * 表id
     */
    private Long id;

    /**
     * 人名拼音（用来登录）
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 登录密码
     */
    private String loginPassword;

    /**
     * 用户状态 1.正常 2.禁用
     */
    private Integer userStatus;

    /**
     * 用户类型 1.正常用户 2.测试用户
     */
    private Integer userType;




}