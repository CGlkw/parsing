package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.ByteUtils;

@Factory("toFloat")
public class ToFloatPars extends AbstractParsing{
	@Override
	public Object pars(byte[] data) {
		if (isAllZero(data)) 
			return "0";
		return ByteUtils.bytes4ToFloat2(data, 0);
	}
}
