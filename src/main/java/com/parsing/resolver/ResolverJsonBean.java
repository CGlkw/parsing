package com.parsing.resolver;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.parsing.pars.ParsProduce;

public class ResolverJsonBean extends AbsResolverBean {
	private String key;
	private List<ResolverJsonBean> jsonObj;
	private String includeId;

	/**
	 * 解析json数据
	 * @param object
	 * @param result
	 */
	@Override
	public void parse(JSONObject object, Map<String, Object> result) {
		if (columnName != null) {
			String value = object.getString(key);

			if (includeId != null) {
				InitialBytesData resolverBytesBean = (InitialBytesData)get(includeId);
				resolverBytesBean.initialStringData(result, value);

			} else {				
				result.put(columnName, toParse(value));
			}

		} else {
			JSONObject jsonObject2 = object.getJSONObject(key);
			for (ResolverJsonBean resolverJsonBean : jsonObj)
				resolverJsonBean.parse(jsonObject2, result);
			
		}
	}

	@Override
	public void parse(byte[] data, Map<String, Object> result) {

	}

	public ResolverJsonBean(String columnName, String key, String includeId,String sign,String parse) {
		super();
		this.columnName = columnName;
		this.key = key;
		this.includeId = includeId;
		this.sign = sign;
		this.parsing = parse!=null? ParsProduce.getPars(parse):null;
	}
	public ResolverJsonBean(String key, List<ResolverJsonBean> jsonObj,String sign ) {
		super();
		this.key = key;
		this.jsonObj = jsonObj;
		this.sign = sign;
	}


	public ResolverJsonBean() {

	}

}
