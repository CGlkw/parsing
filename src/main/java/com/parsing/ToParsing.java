package com.parsing;

import com.parsing.resolver.ParseMap;
import com.parsing.resolver.ResultBeanMapper;

public class ToParsing extends ParseMap {

	public static <T> T toPars(String sign, String data, Class<T> clazz) {
		ResultBeanMapper resultBeanMapper = XmlBeanMap.get(sign);		
		return resultBeanMapper.getResult( sign,  data, clazz);
	}

	public static <T> T toPars(String sign, byte[] data, Class<T> clazz) {
		ResultBeanMapper resultBeanMapper = XmlBeanMap.get(sign);		
		return resultBeanMapper.getResult( sign,  data, clazz);
	}

	public static Object toPars(String sign, String data) {
		ResultBeanMapper resultBeanMapper = XmlBeanMap.get(sign);
		return resultBeanMapper.getResult(sign,data);
	}

	public static Object toPars(String sign, byte[] data) {
		ResultBeanMapper resultBeanMapper = XmlBeanMap.get(sign);
		return resultBeanMapper.getResult(sign,data);
	}


}
