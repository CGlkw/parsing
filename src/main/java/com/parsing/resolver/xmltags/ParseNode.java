package com.parsing.resolver.xmltags;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface ParseNode {
	void judgeNodeParse(Map<String, Object> param, byte[] data);

	void judgeNodeParse(Map<String, Object> param, JSONObject jsonObject);
}
