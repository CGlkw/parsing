package com.parsing.resolver;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.parsing.pars.ParsProduce;
import com.parsing.pars.factory.Parsing;

public class ResolverByteBean extends AbsResolverBean {
	private String parsType;
	private int index;
	private int offset;
	private List<ResolverByteBean> bitBean;
	private boolean isBit = false;

	/**
	 * 解析16进制数据
	 * 
	 * @param data
	 * @param result
	 */
	@Override
	public void parse(byte[] data, Map<String, Object> result) {
		Object pars = null;
		if (!isBit) {
			int byteOffset = offset;
			if (byteOffset == 0)
				byteOffset = data.length - index;

			byte[] parsDatas = Arrays.copyOfRange(data, index, index + byteOffset);

			if (bitBean != null && bitBean.size() != 0)

				for (ResolverByteBean bean : bitBean)
					bean.parse(parsDatas, result);
			else
				pars = parsing.pars(parsDatas);

		} else
			pars = parsing.pars(data, index, offset);

		if (columnName != null && !"".equals(columnName)) {
			result.put(columnName, pars);
			toOperation(result);
		}

	}

	public ResolverByteBean() {

	}

	public ResolverByteBean(String colmnName, String parsType, String index, String offset, String operator,
			boolean isBit, String sign) {
		this.columnName = colmnName;
		this.parsType = parsType;
		this.index = index != null ? Integer.parseInt(index) : 0;
		this.offset = offset != null ? Integer.parseInt(offset) : 0;
		this.parsing = parsType != null ? ParsProduce.getPars(parsType) : null;
		this.operator = operator;
		this.isBit = isBit;
		this.sign = sign;
	}

	public ResolverByteBean(String colmnName, String parsType, String index, String offset,
			List<ResolverByteBean> bitBean, String operator, String sign) {
		this.columnName = colmnName;
		this.parsType = parsType;
		this.index = index != null ? Integer.parseInt(index) : 0;
		this.offset = offset != null ? Integer.parseInt(offset) : 0;
		this.parsing = parsType != null ? ParsProduce.getPars(parsType) : null;
		this.operator = operator;

		this.bitBean = bitBean;
		this.sign = sign;
	}
	
	@Override
	public void parse(JSONObject object, Map<String, Object> result) {

	}

}
