package com.parsing.resolver.xmltags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.parsing.resolver.InitialBytesData;

public class ForeachNode extends AbsParseNode {

	private int index;
	private int endIndex;

	private int itemLength;

	private String includeID;

	private String foreachNode;

	@Override
	public void judgeNodeParse(Map<String, Object> param, byte[] data) {
		int length = data.length - index - endIndex;
		byte[] foreachData = Arrays.copyOfRange(data, index, index + length);
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < length / itemLength; i++) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			int index = i * itemLength;
			byte[] itemData = Arrays.copyOfRange(foreachData, index, index + itemLength);
			InitialBytesData initialBytesData = (InitialBytesData) get(includeID);
			initialBytesData.initialStringData(hashMap, itemData);
			list.add(hashMap);
		}
		param.put(foreachNode, list);
	}

	public ForeachNode() {

	}

	public ForeachNode(String index, String endIndex, String itemLength, String includeID, String foreachNode) {
		super();

		this.index = index != null ? Integer.parseInt(index) : 0;
		this.endIndex = endIndex != null ? Integer.parseInt(endIndex) : 0;
		this.itemLength = itemLength != null ? Integer.parseInt(itemLength) : 0;
		this.includeID = includeID;
		this.foreachNode = foreachNode == null ? includeID : foreachNode;

	}

}
