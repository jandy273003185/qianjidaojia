package com.qifenqian.bms.v2.biz.basedata.certify.bean.domain;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/26 17:58
 */
@ApiModel(value = "处理验证明细Bean")
public class DealIdentityAO implements Serializable {
    private String validateId;
    private String fileId;
    private String memo;

    public String getValidateId() {
        return validateId;
    }

    public void setValidateId(String validateId) {
        this.validateId = validateId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
