package com.gome.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;

public class DecoderUtil<T> {
	public T decodeURI(T t) throws Exception{
		Class<?> classType = t.getClass();
		Field[] fields = classType.getDeclaredFields();
	
		for(Field field :fields){
			if(field.getType() == String.class){
				Method getMethod = classType.getMethod("get" + (new StringBuilder()).append(Character.toUpperCase(field.getName().charAt(0))).append(field.getName().substring(1)).toString(), new Class[]{});
				Method setMethod = classType.getMethod("set" + (new StringBuilder()).append(Character.toUpperCase(field.getName().charAt(0))).append(field.getName().substring(1)).toString(), new Class[]{String.class});
				if(getMethod.invoke(t, new Object[]{}) != null){
					String value = getMethod.invoke(t, new Object[]{}).toString();
					if(!"".equals(value)){
						setMethod.invoke(t, new Object[]{URLDecoder.decode(value, "UTF-8")});//自动装箱
					}
				}
			}
		}
		return null;
	}
}
