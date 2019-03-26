package com.parsing.resolver.xmltags;

import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;

public class DefaultValueNode extends AbsParseNode{

	private String key;
	
	private String value;
	
	private boolean isOgnl;
	
	@Override
	public void judgeNodeParse(Map<String, Object> param, byte[] data) {
		if (isOgnl) {
			Object value = null;
			try {
				value = Ognl.getValue(this.value, ognlContext, param);
			} catch (OgnlException e) {
				e.printStackTrace();
			}
			param.put(key, value);
		}else
			param.put(key, value);
	}

	
	
	public DefaultValueNode() {
	}

	public DefaultValueNode(String key, String value,String ognl) {
		super();
		this.key = key;
		this.value = value;
		this.isOgnl= ognl != null? Boolean.parseBoolean(ognl):false;
	}

}
