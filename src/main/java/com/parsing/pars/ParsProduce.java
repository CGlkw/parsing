package com.parsing.pars;

import com.parsing.RunParse;
import com.parsing.pars.factory.Parsing;
public class ParsProduce {

	public static Parsing getPars(String pars) {
		return (Parsing) RunParse.getBean(pars);
	}
}
