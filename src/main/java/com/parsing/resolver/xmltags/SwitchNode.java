package com.parsing.resolver.xmltags;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.parsing.resolver.InitialBytesData;
import com.parsing.resolver.InitialJsonData;

public class SwitchNode extends AbsParseNode {

	private String key;

	private Map<String, String> caseNode;

	private String defaultNode;

	@Override
	public void judgeNodeParse(Map<String, Object> param, byte[] data) {
		String key = (String) param.get(this.key);
		String id = caseNode.get(key);
		InitialBytesData initialBytesData;
		if (id == null)
			initialBytesData = (InitialBytesData)get(defaultNode);
		else
			initialBytesData = (InitialBytesData)get(id);

		if (initialBytesData != null) {
			initialBytesData.initialStringData(param, data);
		}

	}

	@Override
	public void judgeNodeParse(Map<String, Object> param, JSONObject jsonObject) {
		super.judgeNodeParse(param, jsonObject);
		String key = (String) param.get(this.key);
		String id = caseNode.get(key);
		InitialJsonData initialJsonData;
		if (id == null)
			initialJsonData = (InitialJsonData) get(defaultNode);
		else
			initialJsonData = (InitialJsonData) get(id);
		if (initialJsonData != null) {
			initialJsonData.initialJsonData(param, jsonObject);
		}
	}

	public SwitchNode(String key, Map<String, String> caseNode, String defaultNode) {

		this.key = key;
		this.caseNode = caseNode;
		this.defaultNode = defaultNode;

	}

	public SwitchNode() {
	}

}
