package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.ByteUtils;

@Factory("toInt2")
public class ToIntPars2 extends AbstractParsing{
	@Override
	public Object pars(byte[] data) {
		return ByteUtils.toInt(data, 0);
	}
}
