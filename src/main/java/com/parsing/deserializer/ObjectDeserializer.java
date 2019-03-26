package com.parsing.deserializer;

import java.util.Map;

public interface ObjectDeserializer {
	public Object convertMap(Map<String, Object> map);
}
