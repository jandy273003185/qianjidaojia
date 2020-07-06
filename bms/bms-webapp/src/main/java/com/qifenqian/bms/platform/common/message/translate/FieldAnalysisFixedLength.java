package com.qifenqian.bms.platform.common.message.translate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.qifenqian.bms.platform.common.message.interceptor.MessageInterceptorInterface;
import com.qifenqian.bms.platform.common.message.type.PadType;

/**
 * 
 * @project gyzb-platform-common
 * @fileName FieldAnalysis.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
public class FieldAnalysisFixedLength implements Serializable{

	private static final long serialVersionUID = 5344263491780567760L;
	
	/** 属性名称 */
    private String name;
    /** 该字段的起始位置*/
    private int startPos;
    /** 该字段的长度*/
    private int length;
    /** 该字段的填充类型*/
    private PadType padType;
    /** 该字段的填充物*/
    private char material;
    /** 字段类型*/
    private Class<?> fieldClass;
    /** 匹配正则表达式*/
    private String regex;
    
    /** 获值之前*/
    private List<Class<? extends MessageInterceptorInterface>> beforeGetInterceptors;
    /** 获值之后*/
    private List<Class<? extends MessageInterceptorInterface>> afterGetInterceptors;
    /** 赋值之前*/
    private List<Class<? extends MessageInterceptorInterface>> beforeSetInterceptors;
    /** 赋值之后*/
    private List<Class<? extends MessageInterceptorInterface>> afterSetInterceptors;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getStartPos() {
        return startPos;
    }
    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public PadType getPadType() {
        return padType;
    }
    public void setPadType(PadType padType) {
        this.padType = padType;
    }
    public char getMaterial() {
        return material;
    }
    public void setMaterial(char material) {
        this.material = material;
    }
    public Class<?> getFieldClass() {
        return fieldClass;
    }
    public void setFieldClass(Class<?> fieldClass) {
        this.fieldClass = fieldClass;
    }
    public List<Class<? extends MessageInterceptorInterface>> getBeforeGetInterceptors() {
        return beforeGetInterceptors;
    }
    public void addBeforeGetInterceptor(Class<? extends MessageInterceptorInterface> interceptor) {
        if (beforeGetInterceptors == null) {
            beforeGetInterceptors = new ArrayList<Class<? extends MessageInterceptorInterface>>();
        }
        beforeGetInterceptors.add(interceptor);
    }
    public List<Class<? extends MessageInterceptorInterface>> getAfterGetInterceptors() {
        return afterGetInterceptors;
    }
    public void addAfterGetInterceptor(Class<? extends MessageInterceptorInterface> interceptor) {
        if (afterGetInterceptors == null) {
            afterGetInterceptors = new ArrayList<Class<? extends MessageInterceptorInterface>>();
        }
        afterGetInterceptors.add(interceptor);
    }
    public List<Class<? extends MessageInterceptorInterface>> getBeforeSetInterceptors() {
        return beforeSetInterceptors;
    }
    public void addBeforeSetInterceptor(Class<? extends MessageInterceptorInterface> interceptor) {
        if (beforeSetInterceptors == null) {
            beforeSetInterceptors = new ArrayList<Class<? extends MessageInterceptorInterface>>();
        }
        beforeSetInterceptors.add(interceptor);
    }
    public List<Class<? extends MessageInterceptorInterface>> getAfterSetInterceptors() {
        return afterSetInterceptors;
    }
    public void addAfterSetInterceptor(Class<? extends MessageInterceptorInterface> interceptor) {
        if (afterSetInterceptors == null) {
            afterSetInterceptors = new ArrayList<Class<? extends MessageInterceptorInterface>>();
        }
        afterSetInterceptors.add(interceptor);
    }
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
	public void setBeforeGetInterceptors(
			List<Class<? extends MessageInterceptorInterface>> beforeGetInterceptors) {
		this.beforeGetInterceptors = beforeGetInterceptors;
	}
	public void setAfterGetInterceptors(
			List<Class<? extends MessageInterceptorInterface>> afterGetInterceptors) {
		this.afterGetInterceptors = afterGetInterceptors;
	}
	public void setBeforeSetInterceptors(
			List<Class<? extends MessageInterceptorInterface>> beforeSetInterceptors) {
		this.beforeSetInterceptors = beforeSetInterceptors;
	}
	public void setAfterSetInterceptors(
			List<Class<? extends MessageInterceptorInterface>> afterSetInterceptors) {
		this.afterSetInterceptors = afterSetInterceptors;
	}
}


