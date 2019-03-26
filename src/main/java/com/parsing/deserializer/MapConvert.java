package com.parsing.deserializer;

import java.util.Map;

import com.parsing.exception.UnknowClassTypeException;

public class MapConvert {
	
	private static final ParserConfig parse = new ParserConfig();
	
	public static <T> T toParse(Map<String, Object> map ,Class<T> clazz) {
		try {
			ObjectDeserializer deserializer = parse.getDeserializer(clazz);
			Object convertMap = deserializer.convertMap(map);
			return (T)convertMap;
		} catch (UnknowClassTypeException e) {
			e.printStackTrace();
		}
		return null;
	}
}
