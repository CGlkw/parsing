package com.parsing.resolver;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.parsing.initialData.InitialDataProduce;
import com.parsing.initialData.factory.InitialData;
import com.parsing.resolver.verifytags.VerifyData;
import com.parsing.resolver.xmltags.ParseNode;

public class InitialBytesData extends AbsInitialData {
	private String id;

	private List<ResolverByteBean> resolverBeans;

	/**
	 * 格式化数据
	 * @param result
	 * @param data
	 */
	@Override
	public void initialStringData(Map<String, Object> result, String data) {
		
		if (data!=null){
			byte[] afterInitialData = initialData.initialData(data);
			// 处理格式化话后的数据
			initialStringData(result, afterInitialData);
		}
	}

	/**
	 * 处理格式化后的数据
	 * @param result
	 * @param data
	 */
	@Override
	public void initialStringData(Map<String, Object> result, byte[] data) {
		//数据效验
		if(!dataVerify(data)) {
			result.put("verify", false);
			return;
		}
			
		// 处理格式化话后的数据并返回
		if (resolverBeans != null) {
			for (ResolverByteBean byteBean : resolverBeans)
				byteBean.parse(data, result);
		}

		//处理判断循环标签
		parseJudgeNode(result,data);
	}

	public InitialBytesData(String id, String initial, List<ResolverByteBean> resolverBeans,
			List<ParseNode> parseNodes,List<VerifyData> verifyDatas) {
		super();
		this.id = id;
		this.initialData = initial != null? InitialDataProduce.getInitialData(initial):null;
		this.resolverBeans = resolverBeans;
		this.parseNodes = parseNodes;
		this.verifyDatas = verifyDatas;
	}

	public InitialBytesData(String id, String initial, List<ResolverByteBean> resolverBeans) {
		super();
		this.id = id;
		this.initialData = initial != null? InitialDataProduce.getInitialData(initial):null;
		this.resolverBeans = resolverBeans;
	}

	public InitialBytesData(String initial, List<ResolverByteBean> resolverBeans) {
		this.initialData = initial != null?InitialDataProduce.getInitialData(initial):null;
		this.resolverBeans = resolverBeans;
	}

	public InitialBytesData() {

	}
	
	@Override
	public void initialJsonData(Map<String, Object> result, String jsonData) {

	}

	@Override
	public void initialJsonData(Map<String, Object> result, JSONObject jsonObject) {

	}

	public String getId() {
		return id;
	}

	
}
