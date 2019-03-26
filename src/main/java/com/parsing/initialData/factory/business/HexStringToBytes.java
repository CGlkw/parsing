package com.parsing.initialData.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.initialData.factory.AbstractInitailData;

@Factory("HexStringToBytes")
public class HexStringToBytes extends AbstractInitailData {
	
	@Override
	public byte[] initialData(String data) {
		return hexStringToBytes(data);
	}
	
}
