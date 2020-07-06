package com.sevenpay.agentmanager.core.exception;

/**
 * <class description>
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String uri;

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

    public BizException(String code, String msg, String uri) {
        super(msg);
        this.uri = uri;
        this.code = code;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
