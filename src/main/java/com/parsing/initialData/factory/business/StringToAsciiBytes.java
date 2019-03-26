package com.parsing.initialData.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.initialData.factory.AbstractInitailData;

/**
 * 字符串转asciibytes                        <br/>
 * param   (52865360\r18)320               <br/>
 * value   [40, 53, 50, 56, 54, 53, 51, 54, 48, 92, 114, 49, 56, 41, 51, 50, 48]
 * @author CGlkw
 *
 */
@Factory("stringToAsciiBytes")
public class StringToAsciiBytes extends AbstractInitailData {

	
	@Override
	public byte[] initialData(String data) {
		
		return stringToAsciiBytes(data);
	}
}
