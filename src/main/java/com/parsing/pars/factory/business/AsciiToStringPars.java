package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.AsciiUtils;

@Factory("asciiToString")
public class AsciiToStringPars extends AbstractParsing {
	@Override
	public String pars(byte[] data) {
		return AsciiUtils.asciiBytesToString(trim(data), false);
	}
}
