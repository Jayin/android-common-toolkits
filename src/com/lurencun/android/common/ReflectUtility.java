package com.lurencun.android.common;

import java.lang.reflect.Field;

/**
 * @author : 桥下一粒砂 chenyoca@gmail.com
 * date   : 2013-4-7
 * 反射工具类
 */
public class ReflectUtility {
	
	/**
	 * 取得指定对象的指定成员名的值
	 * @param object 指定的对象
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 */
	public static Object getFieldValue(Object object,String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}
	
	/**
	 * 对指定对象设置其指定成员变量的值
	 * @param object
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setFieldValue(Object object,String fieldName,Object value) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(object, value);
	}
}
