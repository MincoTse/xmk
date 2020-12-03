package com.minco.zhushou.entity;

import com.xmk.common.base.DBaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 用户权限关系表
 * 实体类
 *
 * @author minco
 * @since 2020-11-19 20:17:19
 */
@Data
@Accessors(chain = true)
public class UserPermission extends DBaseEntity implements Serializable {

    private static final long serialVersionUID = 118486990985639447L;

    /**
     * 表id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 权限id
     */
    private Long permissionId;


}