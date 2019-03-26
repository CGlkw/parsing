package com.parsing.initialData.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.initialData.factory.AbstractInitailData;
/**
 * 将转码的Ascii数组转换为统一数组   <br/>
 * param  35 39
 * value  59
 * @author CGlkw
 *
 */
@Factory("AsciiStringTobytes")
public class AsciiStringTobytes extends AbstractInitailData {
	
	@Override
	public byte[] initialData(String data) {

		return dataToBytes(data);
	}
	
}
