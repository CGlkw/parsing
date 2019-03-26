package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.ByteUtils;

@Factory("toHexString")
public class ToHexStringPars extends AbstractParsing {

	@Override
	public String pars(byte[] data) {

		return ByteUtils.bytesToHexString(data).replaceAll(" ", "");
	}

}
