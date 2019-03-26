package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.AsciiUtils;

@Factory("asciiBytesToString")
public class AsciiBytesToString extends AbstractParsing{
	 
	 @Override
	public String pars(byte[] data) {

		return AsciiUtils.asciiBytesToString(data, false);
	}
}
