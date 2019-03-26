package com.parsing.resolver.verifytags;

import com.parsing.utils.ByteUtils;

public class TailVerify extends AbsVerifyData {
	private byte[] text;

	@Override
	public boolean verify(byte[] data) {
		int i = data.length - 1;
		int t = text.length - 1;
		for (; t >= 0; i--, t--) {
			if (text[t] != data[i]) {
				return false;
			}
		}
		return true;
	}

	public TailVerify(String text) {
		this.text = text != null ? ByteUtils.hexStringToBytes(text) : null;
	}

	public TailVerify() {

	}
}
