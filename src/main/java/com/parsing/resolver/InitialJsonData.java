package com.parsing.resolver;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class InitialJsonData extends AbsInitialData {
	private String id;

	private List<ResolverJsonBean> resolverJsonBeans;

	/**
	 * 格式化数据
	 * @param result
	 * @param jsonData
	 */
	@Override
	public void initialJsonData(Map<String, Object> result, String jsonData) {
		if (initialData != null) {
			jsonData = initialData.initialJsonData(jsonData);
		}
		JSONObject jsonObject = JSON.parseObject(jsonData);
		initialJsonData(result,jsonObject);
	}
 
	/**
	 * 解析数据
	 * @param result
	 * @param jsonObject
	 */
	@Override
	public void initialJsonData(Map<String, Object> result, JSONObject jsonObject) {
		for (ResolverJsonBean resolverJsonBean : resolverJsonBeans) {
			resolverJsonBean.parse(jsonObject, result);
		}
		//处理判断标签
		parseJudgeNode(result,jsonObject);
	}


	public InitialJsonData() {

	}

	public InitialJsonData(String id, List<ResolverJsonBean> resolverJsonBeans) {
		super();
		this.id = id;
		this.resolverJsonBeans = resolverJsonBeans;
	}

	public String getId() {
		return id;
	}

	@Override
	public void initialStringData(Map<String, Object> result, String data) {

	}

	@Override
	public void initialStringData(Map<String, Object> result, byte[] data) {

	}
}
