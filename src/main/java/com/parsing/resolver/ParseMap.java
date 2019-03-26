package com.parsing.resolver;

import java.util.HashMap;
import java.util.Map;

import ognl.OgnlContext;

public class ParseMap {

	public static HashMap<String, ResultBeanMapper> XmlBeanMap = new HashMap<String, ResultBeanMapper>();

	protected static Map<String, AbsInitialData> resolverBeanMap = new HashMap<String, AbsInitialData>();

	public static OgnlContext ognlContext = new OgnlContext();

	protected String sign;

	public static void putOgnlContext(String key,Object value) {
		ognlContext.put(key, value);
	}
	
	public ParseMap() {
		
	}

	public ParseMap(String sign) {
		this.sign = sign;
	}

	public void put(String key, AbsInitialData value) {
		key = sign + "_" + key;

		resolverBeanMap.put(key, value);
	}

	public AbsInitialData get(Object key) {
		key = sign + "_" + key;
		return resolverBeanMap.get(key);
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
