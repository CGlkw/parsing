package com.parsing.initialData.factory.business;

import com.alibaba.fastjson.util.TypeUtils;
import com.parsing.beanFactory.Factory;
import com.parsing.initialData.factory.AbstractInitailData;
@Factory("Base64ToBytes")
public class Base64ToBytes extends AbstractInitailData {

	@Override
	public byte[] initialData(String data) {
	
		return TypeUtils.castToBytes(data);
	}
}
