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
@Factory("AsciiBytesToBytes")
public class AsciiBytesToBytes extends AbstractInitailData {
	
	@Override
	public byte[] initialData(byte[] data) {
		
		return hexStringToBytes(asciiBytesToString(data, false));
	}
	
}
