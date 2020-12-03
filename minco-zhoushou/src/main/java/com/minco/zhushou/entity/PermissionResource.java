package com.minco.zhushou.entity;

import com.xmk.common.base.DBaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 资源表（权限）
 * 实体类
 *
 * @author minco
 * @since 2020-11-28 21:30:24
 */
@Data
@Accessors(chain = true)
public class PermissionResource extends DBaseEntity implements Serializable {

    private static final long serialVersionUID = 801236958747214451L;

    private Long id;

    private String resourceName;

    /**
     * 1.标题菜单 2.菜单 3.按钮
     */
    private String resourceType;

    private String resourceUrl;

    private Long parentId;

    private Integer resourceLevel;

    private String resourceDesc;


}