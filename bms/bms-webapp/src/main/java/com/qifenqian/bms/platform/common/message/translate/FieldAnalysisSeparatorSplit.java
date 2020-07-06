package com.qifenqian.bms.platform.common.message.translate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.qifenqian.bms.platform.common.message.interceptor.MessageInterceptorInterface;
import com.qifenqian.bms.platform.common.message.type.PadType;

/**
 * 解析规则 分割
 * @project gyzb-platform-common
 * @fileName FieldAnalysisSeparatorSplit.java
 * @author huiquan.ma
 * @date 2016年2月22日
 * @memo
 */
public class FieldAnalysisSeparatorSplit implements Serializable{

	private static final long serialVersionUID = 5344263491780567760L;
	
    /** 属性名称 */
    private String name;
    /** 栏位代码*/
    public String fieldCode;
    /** 分隔符*/
    public String separator;
    /** 分割序号*/
    public int index;
    /** 该字段的填充类型*/
    private PadType padType;
    /** 该字段的填充物*/
    private char material;
    /** 字段类型*/
    private Class<?> fieldClass;
    /** 匹配正则表达式*/
    private String regex;
    /** 字段栏位索引*/
    private String columnId;
    
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
	public String getSeparator() {
		return separator;
	}
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
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
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
}


