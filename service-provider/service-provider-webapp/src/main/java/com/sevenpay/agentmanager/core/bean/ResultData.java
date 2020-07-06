package com.sevenpay.agentmanager.core.bean;

/**
 * ClassName：ResultData
 * Description：TODO
 * Author: LiBin
 * Date：2019/12/30 5:33 下午
 */

import java.io.Serializable;
import java.util.HashMap;

public class ResultData extends HashMap<String, Object> implements Serializable {
    public ResultData() {
        put("code", "200");
    }

    public static ResultData error() {
        return error("500", "系统繁忙");
    }

    public static ResultData error(String msg) {
        return error("500", msg);
    }

    public static ResultData error(String code, String msg) {
        ResultData resultData = new ResultData();
        resultData.put("code", code);
        resultData.put("msg", msg);
        return resultData;
    }

    public static ResultData error(String code, String msg, Object object) {
        ResultData resultData = new ResultData();
        resultData.put("code", code);
        resultData.put("msg", msg);
        resultData.put("data", object);
        return resultData;
    }

    public static ResultData success(String msg, Object object) {
        ResultData resultData = new ResultData();
        resultData.put("code", "200");
        resultData.put("msg", msg);
        resultData.put("data", object);
        return resultData;
    }

    public static ResultData success(String code, String msg) {
        ResultData resultData = new ResultData();
        resultData.put("code", code);
        resultData.put("msg", msg);
        return resultData;
    }

    public static ResultData success(Object obj) {
        ResultData resultData = new ResultData();
        resultData.put("code", 200);
        resultData.put("msg", "操作成功");
        resultData.put("data", obj);
        return resultData;
    }

    public static ResultData success() {
        ResultData resultData = new ResultData();
        resultData.put("msg", "操作成功");
        resultData.put("code", 200);
        return resultData;
    }

    public ResultData put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Object get(Object key) {
        return super.get(key);
    }
}
