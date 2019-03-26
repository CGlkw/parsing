package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.AsciiUtils;

@Factory("hexStringToInt")
public class HexStringToInt extends AbstractParsing{

	@Override
	public Object pars(byte[] data) {
		String s = AsciiUtils.asciiBytesToString(data, false);
		return Integer.parseInt(s, 16);
	}
}
