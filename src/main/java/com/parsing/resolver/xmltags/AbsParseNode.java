package com.parsing.resolver.xmltags;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.parsing.resolver.ParseMap;

public abstract class AbsParseNode extends ParseMap implements ParseNode {

	@Override
	public void judgeNodeParse(Map<String, Object> param, byte[] data) {

	}

	@Override
	public void judgeNodeParse(Map<String, Object> param, JSONObject jsonObject) {

	}
}
