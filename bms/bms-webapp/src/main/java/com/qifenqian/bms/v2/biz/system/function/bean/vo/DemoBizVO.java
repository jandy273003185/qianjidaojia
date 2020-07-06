package com.qifenqian.bms.v2.biz.system.function.bean.vo;

import java.io.Serializable;

/**
 * ClassName：DemoBizVO
 * Description：DO 同理与DO用于结果集展示,属性展示出前端需要的字段即可.
 * Author: LiBin
 * Date：2019/12/27 2:05 下午
 */
public class DemoBizVO implements Serializable {
    private static final long serialVersionUID = -7950633777143551740L;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
