package com.zxiot.parsing;

import com.parsing.RunParse;
import com.parsing.ToParsing;

public class TestA {

	public static void main(String[] args) {
		RunParse runParse = new RunParse();
		runParse.init("classpath*:parse/*.xml");
		String test = "AA55031402BB";
		
		long start = System.currentTimeMillis();
		User pars = ToParsing.toPars("CC", test,User.class);
		long end = System.currentTimeMillis();
		test = "AA55122222BB";
		User pars2 = ToParsing.toPars("CC", test,User.class);
		long end1 = System.currentTimeMillis();
		System.out.println(pars); 
		System.out.println(pars2); 
		System.out.println(end-start);
		System.out.println(end1-end);
	}


}
