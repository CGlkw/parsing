package com.parsing.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class FieldInfo {
	
	public String name;
	public Method setMethod;
	public Field field;
	
	public String methodName;
	public String propertyType;
	public Class<?>   fieldClass;
	public Type       fieldType;
	
	public FieldInfo(Field field,Method setMethod){
		this.name = field.getName();
		this.setMethod =setMethod;
		this.field= field;
		this.methodName = setMethod.getName();
		this.propertyType = field.getAnnotatedType().getType().getTypeName();
		this.fieldClass = field.getDeclaringClass();
		this.fieldType = field.getType();
		
	}
	public FieldInfo(Field field,Method setMethod,String name,Type fieldType){
		this.name = name;
		this.setMethod =setMethod;
		this.field= field;
		this.methodName = setMethod.getName();
		this.propertyType = field.getAnnotatedType().getType().getTypeName();
		this.fieldClass = field.getDeclaringClass();
		this.fieldType = fieldType;
		
	}
	@Override
	public String toString() {
		return "FieldInfo [name=" + name + ", setMethod=" + setMethod + ", field=" + field + ", methodName="
				+ methodName + ", propertyType=" + propertyType + ", fieldClass=" + fieldClass + ", fieldType="
				+ fieldType + "]";
	}
	
	
}
