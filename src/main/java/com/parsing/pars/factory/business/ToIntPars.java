package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.ByteUtils;

@Factory("toInt")
public class ToIntPars extends AbstractParsing{
	@Override
	public Object pars(byte[] data) {
		return ByteUtils.toInt2(data, 0);
	}
}
