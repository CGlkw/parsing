package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;

@Factory("toByte")
public class ToByte extends AbstractParsing{
	@Override
	public Object pars(byte[] data) {
		return data[0];
	}
}
