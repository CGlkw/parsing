package com.parsing.resolver.xmltags;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.parsing.resolver.InitialBytesData;
import com.parsing.resolver.InitialJsonData;

import ognl.Ognl;
import ognl.OgnlException;


public class IfNode extends AbsParseNode {

	private String text;

	private String ifNode;

	@Override
	public void judgeNodeParse(Map<String, Object> param, byte[] data) {
		boolean b = doJudge(param);
		if (b) {
			InitialBytesData resolverBytesBean = (InitialBytesData)get(ifNode);
			resolverBytesBean.initialStringData(param, data);
		}
	}

	@Override
	public void judgeNodeParse(Map<String, Object> param, JSONObject jsonObject) {
		boolean b = doJudge(param);
		if (b) {
			InitialJsonData initialJsonData = (InitialJsonData)get(ifNode);
			initialJsonData.initialJsonData(param,jsonObject);
		}
	}

	public boolean doJudge(Map<String, Object> param){
		Object value = null;
		try {
			value = Ognl.getValue(text, ognlContext, param);
		} catch (OgnlException e) {
			e.printStackTrace();
		}
		if (value instanceof Boolean) {
			return (Boolean)value;
		}
		return false;
	}

	public IfNode(String text, String ifNode) {
		super();
		this.text = text;
		this.ifNode = ifNode;
	}

	public IfNode() {

	}

}
