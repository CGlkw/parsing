package com.parsing.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JavaBeanInfo {
	public String name;
	public final Class<?> clazz;
	public String className;
	public String pathName;
	public ArrayList<Field> fieldList;
	public ArrayList<Method> methodList;
	
	public ArrayList<FieldInfo> fieldInfoList;
	
	public int                        variantIndex = 0;
    private final Map<String, Integer> variants     = new HashMap<String, Integer>();
	
	public JavaBeanInfo(Class<?> clazz){
		this.clazz = clazz;
		this.className = clazz.getName();
		this.pathName = className.replace(".", "/");
		this.name = clazz.getSimpleName();
		
		this.fieldList = new ArrayList<>();
		getFields(fieldList, clazz);
		this.methodList = new ArrayList<>();
		getMethods(methodList, clazz);
		this.fieldInfoList = getFieldInfo(fieldList);
	}
	
	
	/**
	 * 递归获取所有子类含父类属性   
	 * @param list
	 * @param clazz
	 */
	public void getFields(ArrayList<Field> list, Class<?> clazz) {
		Field[] declaredFields = clazz.getDeclaredFields();		
		list.addAll(Arrays.asList(declaredFields));
		Class<?> superclass = clazz.getSuperclass();
		if (superclass != null) {
			getFields(list, superclass);
		}
	}

	/**
	 * 递归获取所有子类含父类的方法   
	 * @param list
	 * @param clazz
	 */
	public void getMethods(ArrayList<Method> list, Class<?> clazz) {
		Method[] declaredMethods = clazz.getDeclaredMethods();		
		list.addAll(Arrays.asList(declaredMethods));
		Class<?> superclass = clazz.getSuperclass();
		if (superclass != null) {
			getMethods(list, superclass);
		}
	}
	
	public ArrayList<FieldInfo> getFieldInfo(ArrayList<Field> list){
		ArrayList<FieldInfo> fieldInfoList = new ArrayList<>();
		for (Field field : list) {
			String name = field.getName();
			String _name = removeLine(name);
			Class<?> type = field.getType();
			Method method = getMethod(_name, type);
			FieldInfo fieldInfo = new FieldInfo(field, method, _name, type);
			fieldInfoList.add(fieldInfo);
		}			
		return fieldInfoList;
	}
	
	/**
     * 处理字符串  如：  abc_dex ---> abcDex
     * @param str
     * @return
     */
    public String removeLine(String str){
		if(null != str && str.contains("_")){
			int i = str.indexOf("_");
			char ch = str.charAt(i+1);
			char newCh = (ch+"").substring(0, 1).toUpperCase().toCharArray()[0];
			String newStr = str.replace(str.charAt(i+1), newCh);
			String newStr2 = newStr.replace("_", "");
			return newStr2;
		}
		return str;
	}
	
    public Method getMethod(String name,Class<?> type){
    	StringBuilder sb = new StringBuilder();
    	sb.append("set");
    	if(type == boolean.class) {
    		if(name.startsWith("is")) {
    			name = name.substring(2);
    		}
    	}
    	sb.append(name.substring(0, 1).toUpperCase());
    	sb.append(name.substring(1));	
    	try {
			return clazz.getMethod(sb.toString(),type);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return null;
    }
    public int var(String name, int increment) {
        Integer i = variants.get(name);
        if (i == null) {
            variants.put(name, variantIndex);
            variantIndex += increment;
        }
        i = variants.get(name);
        return i.intValue();
    }

    public int var(String name) {
        Integer i = variants.get(name);
        if (i == null) {
            variants.put(name, variantIndex++);
        }
        i = variants.get(name);
        return i.intValue();
    }

	@Override
	public String toString() {
		return "JavaBeanInfo [clazz=" + clazz + ", className=" + className + ", pathName=" + pathName + ", name="
				+ name + ", fieldInfoList=" + fieldInfoList + "]";
	}
    
}
