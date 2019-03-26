package com.parsing.resolver.verifytags;

import java.util.Arrays;

import com.parsing.utils.ByteUtils;

public class LengthVerify extends AbsVerifyData {

	private int start;

	private int end;

	private int index;

	private int offset;

	@Override
	public boolean verify(byte[] data) {
		int l = data.length;
		byte[] parsDatas = index < 0 ? Arrays.copyOfRange(data, l + index, l + index + offset)
				: Arrays.copyOfRange(data, index, index + offset);
		int ls = ByteUtils.toInt2(parsDatas, 0);
		return ls == l - start - end;
	}

	public LengthVerify(String start, String end, String index, String offset) {
		super();
		this.start = start != null ? Integer.parseInt(start) : 0;
		this.end = end != null ? Integer.parseInt(end) : 0;
		this.index = index != null ? Integer.parseInt(index) : 0;
		this.offset = offset != null ? Integer.parseInt(offset) : 0;
	}

	public LengthVerify() {

	}

}
