package com.minco.zhushou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xmk.common.base.DBaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 资源表（权限）
 * 实体类
 *
 * @author minco
 * @since 2020-11-19 20:17:38
 */
@Data
@TableName("tse_resource")
@Accessors(chain = true)
public class BackResource extends DBaseEntity implements Serializable {

    private static final long serialVersionUID = 620575284489602104L;

    private Long id;

    private String resourceName;

    /**
     * 资源类型
     * @see
     */
    private Integer resourceType;

    private String resourceUrl;

    private Long parentId;

    private Integer resourceLevel;

    private String resourceDesc;

}