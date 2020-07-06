package com.qifenqian.bms.v2.common.mvc.bean;

import java.io.Serializable;

/**
 * @author LiBin
 * @Description: 分页查询类
 * @date 2020/4/2 09:14
 */
public class PageQuery implements Serializable {
    private Integer pageNum = 0;
    private Integer pageSize = 10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
