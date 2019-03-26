package com.parsing.initialData.factory.business;


import com.alibaba.fastjson.util.TypeUtils;
import com.parsing.beanFactory.Factory;
import com.parsing.initialData.factory.AbstractInitailData;

@Factory("Base64ToBytes2")
public class Base64ToBytes2 extends AbstractInitailData {

	@Override
	public byte[] initialData(String data) {
		
		return byte2Tobyte(TypeUtils.castToBytes(data));
	}
	
	public byte[] byte2Tobyte(byte[] data) {
		byte[] d = new byte[data.length/2];
		for (int i = 0; i < data.length; i+=2) {
			char cx = (char) data[i];
			char cy = (char) data[i+1];
			d[i/2] = (byte) (charToByte(cx) << 4 | charToByte(cy));
		}
		return d;
	}
}
