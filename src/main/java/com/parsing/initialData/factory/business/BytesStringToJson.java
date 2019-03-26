package com.parsing.initialData.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.initialData.factory.AbstractInitailData;


@Factory("BytesStringToJson")
public class BytesStringToJson extends AbstractInitailData {
	@Override
	public String initialJsonData(String data) {
		String[] split = data.split(",");
		StringBuffer sbu = new StringBuffer();
		for (int i = 0;i< split.length;i++) {
			sbu.append((char)Byte.parseByte(split[i]));			
		}

		return sbu.toString();
	}
}
