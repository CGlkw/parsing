package com.parsing.resolver.verifytags;

import com.parsing.utils.ByteUtils;

public class HeadVerify extends AbsVerifyData {

	private byte[] text;

	@Override
	public boolean verify(byte[] data) {
		for (int i = 0; i < text.length; i++) {
			if (text[i] != data[i]) {
				return false;
			}
		}
		return true;
	}

	public HeadVerify(String text) {
		this.text = text != null ? ByteUtils.hexStringToBytes(text) : null;
	}

	public HeadVerify() {

	}

}
