package com.parsing.pars.factory;


public interface Parsing {
	Object pars(byte[] data);

	Object pars(String data);

	Object pars(byte[] data,int index,int offset);
}
