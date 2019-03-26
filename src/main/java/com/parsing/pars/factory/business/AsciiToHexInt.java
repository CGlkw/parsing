package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.AsciiUtils;
@Factory("asciiToHexInt")
public class AsciiToHexInt extends AbstractParsing{
	@Override
	public Object pars(byte[] data) {
		String s = AsciiUtils.asciiBytesToString(data, false);
		int parseInt = Integer.parseInt(s, 16);
		return parseInt;
	}
}
