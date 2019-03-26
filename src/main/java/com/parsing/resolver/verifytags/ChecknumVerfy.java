package com.parsing.resolver.verifytags;

public class ChecknumVerfy extends AbsVerifyData {
	private int start;

	private int end;

	private int index;

	@Override
	public boolean verify(byte[] data) {
		int l = data.length;
		byte b = index < 0 ? data[l + index] : data[index];
		return b == getCheck(data, start, end);
	}

	/**
	 * 获取校验和
	 * 
	 * @param data
	 * @return
	 */
	public byte getCheck(byte[] data, int start, int end) {
		byte temp = 0;
		for (int i = start; i < data.length - end; i++) {
			temp += data[i];
		}
		return temp;
	}

	public ChecknumVerfy(String start, String end, String index) {
		super();
		this.start = start != null ? Integer.parseInt(start) : 0;
		this.end = end != null ? Integer.parseInt(end) : 0;
		this.index = index != null ? Integer.parseInt(index) : 0;
	}

	public ChecknumVerfy() {

	}


}
