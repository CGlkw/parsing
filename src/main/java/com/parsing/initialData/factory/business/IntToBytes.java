package com.parsing.initialData.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.initialData.factory.AbstractInitailData;
@Factory("intToBytes")
public class IntToBytes extends AbstractInitailData{

	@Override
	public byte[] initialData(String data) {
		
		return toByteArray(Integer.parseInt(data), 2);
	}
	
	/**
	 * int => byte[len], 低位在前, 高位在后, 不足补0
	 * 反转: toInt()
	 */
	public byte[] toByteArray(int iSource, int iArrayLen) {
		byte[] bLocalArr = new byte[iArrayLen];
		for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
			bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);
		}
		return bLocalArr;
	}
}
