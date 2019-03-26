package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.ByteUtils;

@Factory("bit")
public class BitPars extends AbstractParsing {
	@Override
	public Object pars(byte[] data, int index, int offset) {
		int i = ByteUtils.toInt2(data, 0);

		return (i >> index & ((int) Math.pow(2, offset) - 1));
	}

}
