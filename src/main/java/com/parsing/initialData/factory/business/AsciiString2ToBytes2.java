package com.parsing.initialData.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.initialData.factory.AbstractInitailData;

/**
 * 将转码的Ascii数组转换为统一数组   <br/>
 * param  33353339
 * value  59
 * @author CGlkw
 *
 */
@Factory("AsciiString2ToBytes2")
public class AsciiString2ToBytes2 extends AbstractInitailData {
	
	@Override
	public byte[] initialData(String data) {
		
		return data2ToBytes2(data);
	}
	
}
