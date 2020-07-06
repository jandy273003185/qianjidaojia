package com.sevenpay.agentmanager.core.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ClassName：BaseCondition
 * Description：TODO
 * Author: LiBin
 * Date：2020/1/2 1:53 下午
 */
public class BaseCondition implements Serializable {
    private static final long serialVersionUID = 7018475839087842282L;
    @ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;
    @ApiModelProperty(value = "每页条数")
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
