package com.parsing.initialData.factory;
/**
 * 初始化数据，将原始数据转换为统一格式byte数组
 * @author CGlkw
 *
 */
public interface InitialData {
	public byte[] initialData(String data);

	public byte[] initialData(byte[] data);
	
	public String initialJsonData(String data);
}
