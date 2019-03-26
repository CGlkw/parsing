package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.AsciiUtils;
import com.parsing.utils.ByteUtils;

@Factory("toHexString2")
public class ToHexString2Pars extends AbstractParsing {
	@Override
	public String pars(byte[] data) {
		String hex = ByteUtils.bytesToHexString(data);
		return AsciiUtils.asciiToString(hex);
	}
}
