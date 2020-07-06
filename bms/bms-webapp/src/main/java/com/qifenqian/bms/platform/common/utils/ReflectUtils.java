package com.qifenqian.bms.platform.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import com.qifenqian.bms.platform.common.annotation.Comment;

/**
 * 反射相关工具类
 * 	
 * @project gyzb-platform-common
 * @fileName ReflectUtils.java
 * @author huiquan.ma
 * @date 2015年11月18日
 * @memo
 */
@SuppressWarnings("all")
public class ReflectUtils {
	
	/** 判断是否是8种包装类型 */
	public static boolean isWrapClass(Class<?> clazz) {
		try {
			return ((Class<?>) clazz.getField("TYPE").get(null)).isPrimitive();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 根据方法返回指定的Class的Method对象。（不考虑形参类型，若存在多个重载方法，返回第一个）
	 * 
	 * @param clazz
	 * @param methodName
	 * @return
	 */
	public static Method getMethodByName(Class<?> clazz, String methodName) {
		Method[] methods = clazz.getMethods();
		for (Method element : methods) {
			if (element.getName().equals(methodName)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * 根据指定的方法名（getter方法），从提供的bean对象中提取bean成员变量的值。
	 * 
	 * @param bean
	 * @param methodName
	 * @return
	 * @throws Exception
	 */
	public static Object getValueByBean(Object bean, String methodName) throws Exception {
		Object retValue = null;

		if (null == bean) {
			throw new Exception("bean对象为null");
		} else if (StringUtils.isEmpty(methodName)) {
			throw new Exception("未指定方法名");
		}

		Method getter = ReflectUtils.getMethodByName(bean.getClass(), methodName);
		if (getter != null) {
			try {
				retValue = getter.invoke(bean);
			} catch (Exception e) {
				throw new Exception("在调用bean的" + methodName + "方法时发生错误。" + e.getMessage());
			}
		} else {
			throw new Exception(bean.getClass().getName() + "中并未发现名为" + methodName + "的方法");
		}

		return retValue;
	}

	/** 
	 * 获取obj对象fieldName的Field 
	 * @param obj 
	 * @param fieldName 
	 * @return 
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/** 
	 * 获取obj对象fieldName的属性值 
	 * @param obj 
	 * @param fieldName 
	 * @return 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static Object getValueByFieldName(Object obj, String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/** 
	 * 设置obj对象fieldName的属性值 
	 * @param obj 
	 * @param fieldName 
	 * @param value 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void setValueByFieldName(Object obj, String fieldName, Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
	
	/**
	 * 根据枚举类型 及code值获取常量
	 * @param clzz
	 * @param code
	 * @return
	 */
	public static <E extends Enum<E>> E valueof(Class<E> clzz, String code) {
		
		if(StringUtils.isEmpty(code)) {
			throw new NullPointerException("code不能为空");
		}
		try {
			// 循环判断
			List<E> enumList = (List<E>)EnumUtils.getEnumList(clzz);
			if(null != enumList && !enumList.isEmpty()) {
				for(E field : enumList) {
					// code相等，则返回
					if(code.equals(field.getClass().getField(field.name()).getAnnotation(Comment.class).code())) {
						return field;
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("运行异常："+e.getMessage());
			
		} 
		throw new IllegalArgumentException("传入的[class:"+clzz+",code:"+code+"]获取常量为空");
	}
	
	
	/**
	 * 获取code值
	 * @param column
	 * @return
	 */
	public static <E extends Enum<E>> String getCode(Enum<E> column) {
		if(null == column) {
			throw new IllegalArgumentException("传入的column为空");
		}
		try {
			return column.getClass().getField(column.name()).getAnnotation(Comment.class).code();
			
		} catch (Exception e) {
			
			throw new RuntimeException("运行异常："+e.getMessage());
		} 
	}
	
	/**
	 * 获取desc值
	 * @param column
	 * @return
	 */
	public static <E extends Enum<E>> String getDesc(Enum<E> column) {
		if(null == column) {
			throw new IllegalArgumentException("传入的column为空");
		}
		try {
			return column.getClass().getField(column.name()).getAnnotation(Comment.class).desc();
		} catch (Exception e) {
			
			throw new RuntimeException("运行异常："+e.getMessage());
		} 
	}
	
	/**
	 * 获取指定类的 指定索引的泛型 运行时类型
	 * 
	 * 例如 Generic<T, R, E>
	 * 运行时为Generic<String, Integer, Boolean>
	 * 则调用此方法getGenericType(子类, 1) => java.lang.Integer
	 * 
	 * @param clazz
	 * @param genIndex
	 * @return
	 */
	@SuppressWarnings("all")
	public static Class getGenericType(Class clazz, int genIndex) {
		Type genType = clazz.getGenericSuperclass();
		// 若无参数 无泛型 则直接返回Object
		if(!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		// 获取类的所有泛型参数
		Type[] typeClassAry = ((ParameterizedType)genType).getActualTypeArguments();
		if(genIndex <0 || genIndex >= typeClassAry.length) {
			throw new RuntimeException("根据[class:" + clazz + ",genIndex:" + genIndex + "]获取类型越界");
		}
		if(!(typeClassAry[genIndex] instanceof Class)) {
			return Object.class;
		}
		return (Class)typeClassAry[genIndex];
	}
}
