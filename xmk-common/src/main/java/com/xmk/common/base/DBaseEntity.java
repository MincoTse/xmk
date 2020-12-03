package com.xmk.common.base;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

/**
 * 逻辑删除的entity基类
 * <br>
 *
 * @author 明科
 * @create 2020/5/13 15:54
 */

public class DBaseEntity extends BaseEntity {

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
