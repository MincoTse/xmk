package com.minco.zhushou.entity;

import com.xmk.common.base.BaseEntity;
import com.xmk.common.base.DBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * mysql关键字保修自表
 * 实体类
 *
 * @author minco
 * @since 2020-12-01 14:17:42
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class DbKeyWord extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 577771730336659271L;

    private Long id;

    /**
     * 关键字名称
     */
    private String keyWord;

    /**
     * 关键字小写名称
     */
    private String lowKeyWord;



}