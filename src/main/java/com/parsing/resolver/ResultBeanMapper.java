package com.parsing.resolver;

import java.util.HashMap;
import java.util.Map;

import com.parsing.deserializer.MapConvert;

public class ResultBeanMapper extends ParseMap {
	private String resultType;
	private Class<?> type;

	public ResultBeanMapper() {
	}

	public ResultBeanMapper(String resultType) {
		this.resultType = resultType;
		try {
			this.type = resultType != null ? Class.forName(resultType) : null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public <T> T getResult(String sign, String data, Class<T> clazz) {
		Map<String, Object> parsResultMap = getParsResultMap( sign,  data);
		return (T) convertMap(parsResultMap, clazz);
	}
	
	public <T> T getResult(String sign, byte[] data, Class<T> clazz) {
		Map<String, Object> parsResultMap = getParsResultMap( sign,  data);
		return (T) convertMap(parsResultMap, clazz);
	}
	
	public Object getResult(String sign, String data) {
		Map<String, Object> parsResultMap = getParsResultMap( sign,  data);
		return type != null ? convertMap(parsResultMap, type) : parsResultMap;
	}
	public Object getResult(String sign, byte[] data) {
		Map<String, Object> parsResultMap = getParsResultMap(sign,  data);
		return type != null ? convertMap(parsResultMap, type) : parsResultMap;
	}

	public <T> T convertMap(Map<String, Object> map,Class<T> clazz)  {
		if (map == null) {
			return null;
		}
        return MapConvert.toParse(map, clazz);
    }
	
	private Map<String, Object> getParsResultMap(String sign, String data) {
		Map<String, Object> result = new HashMap<String, Object>();
		AbsInitialData absInitialData = resolverBeanMap.get(sign + "_" + sign);
		if (absInitialData instanceof InitialJsonData)
			absInitialData.initialJsonData(result, data);
		else
			absInitialData.initialStringData(result, data);

		return result;
	}

	private Map<String, Object> getParsResultMap(String sign, byte[] data) {
		Map<String, Object> result = new HashMap<String, Object>();
		AbsInitialData absInitialData = resolverBeanMap.get(sign + "_" + sign);
		absInitialData.initialStringData(result, data);
		return result;
	}
		
}
