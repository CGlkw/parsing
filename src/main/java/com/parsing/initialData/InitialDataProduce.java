package com.parsing.initialData;


import com.parsing.RunParse;
import com.parsing.initialData.factory.InitialData;

public class InitialDataProduce {

	public static InitialData getInitialData(String pars) {
		return (InitialData) RunParse.getBean(pars);
	}
}
