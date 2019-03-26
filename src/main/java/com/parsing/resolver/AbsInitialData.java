package com.parsing.resolver;

import com.alibaba.fastjson.JSONObject;
import com.parsing.initialData.InitialDataProduce;
import com.parsing.initialData.factory.InitialData;
import com.parsing.resolver.verifytags.VerifyData;
import com.parsing.resolver.xmltags.ParseNode;

import java.util.List;
import java.util.Map;

public abstract class AbsInitialData extends ParseMap {

	protected InitialData initialData;

	protected List<ParseNode> parseNodes;

	protected List<VerifyData> verifyDatas;

	/**
	 * 数据效验
	 * 
	 * @param data
	 * @return
	 */
	public boolean dataVerify(byte[] data) {
		if (verifyDatas != null && verifyDatas.size() > 0) {
			for (VerifyData v : verifyDatas) {
				boolean verify = v.verify(data);
				if (!verify) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 处理判断循环标签
	 * 
	 * @param result
	 * @param data
	 */
	public void parseJudgeNode(Map<String, Object> result, byte[] data) {
		if (parseNodes.size() > 0)
			for (ParseNode parseNode : parseNodes)
				parseNode.judgeNodeParse(result, data);
	}

	public void parseJudgeNode(Map<String, Object> result, JSONObject jsonObject) {
		if (parseNodes.size() > 0)
			for (ParseNode parseNode : parseNodes)
				parseNode.judgeNodeParse(result, jsonObject);
	}

	public abstract void initialJsonData(Map<String, Object> result, String jsonData);

	public abstract void initialJsonData(Map<String, Object> result, JSONObject jsonObject);

	public abstract void initialStringData(Map<String, Object> result, String data);

	public abstract void initialStringData(Map<String, Object> result, byte[] data);

	public InitialData getInitialData() {
		return initialData;
	}

	public void setInitialData(String initial) {
		this.initialData = initial != null ? InitialDataProduce.getInitialData(initial) : null;
	}

	public List<ParseNode> getParseNodes() {
		return parseNodes;
	}

	public void setParseNodes(List<ParseNode> parseNodes) {
		this.parseNodes = parseNodes;
	}
}
