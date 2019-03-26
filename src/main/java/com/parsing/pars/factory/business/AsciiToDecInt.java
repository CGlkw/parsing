package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.AsciiUtils;
@Factory("asciiToDecInt")
public class AsciiToDecInt extends AbstractParsing{
	@Override
	public Integer pars(byte[] data) {
		String s = AsciiUtils.asciiBytesToString(data, false);
		int parseInt = Integer.parseInt(s, 10);
		return parseInt;
	}
}