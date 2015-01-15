/** 
 * @Project:     sy_oa  
 * @Title:       ReflectHelper.java 
 * @Package:     com.demo.common.util 
 * @Description: 分页处理拦截器
 * @author:      WangYandong
 * @date:        2013-8-4 上午9:27:39 
 * @version:     v1.0
 */
package com.gome.common.page;

import java.lang.reflect.Field;

public class ReflectHelper {
	
	/**
	 * @Title:       getFieldByFieldName 
	 * @Description: 获取obj对象fieldName的Field
	 * @param obj
	 * @param fieldName
	 * @return    
	 * @return:      Field
	 * @throws:      
	 * @author:      WangYandong
	 * @date:        2013-8-4 下午12:56:38
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * @Title:       getValueByFieldName 
	 * @Description: 获取obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException    
	 * @return:      Object
	 * @throws:      
	 * @author:      WangYandong
	 * @date:        2013-8-4 下午12:58:31
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
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
	 * @Title:       setValueByFieldName 
	 * @Description: 设置obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException    
	 * @return:      void
	 * @throws:      
	 * @author:      WangYandong
	 * @date:        2013-8-4 下午12:58:45
	 */
	public static void setValueByFieldName(Object obj, String fieldName,
			Object value) throws SecurityException, NoSuchFieldException,
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
	
}
