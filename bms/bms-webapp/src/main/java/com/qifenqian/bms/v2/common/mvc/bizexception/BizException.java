package com.qifenqian.bms.v2.common.mvc.bizexception;

/**
 * <class description>
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BizException(String msg) {
        super(msg);
    }

    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
