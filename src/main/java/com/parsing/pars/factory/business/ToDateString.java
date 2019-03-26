package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.ByteUtils;
@Factory("toDate")
public class ToDateString extends AbstractParsing{

	@Override
	public String pars(byte[] data) {
		String dataString = ByteUtils.bytesToHexString(data).replaceAll(" ", "");
		int len = data.length;
		switch (len) {
		case 3:
			dataString= dataString.replaceAll("(.{2})(.{2})(.{2})","$1:$2:$3");
			break; 
		case 6:
			dataString= "20"+dataString.replaceAll("(.{2})(.{2})(.{2})(.{2})(.{2})(.{2})","$1-$2-$3 $4:$5:$6");
			break;
		case 7:
			dataString= dataString.replaceAll("(.{4})(.{2})(.{2})(.{2})(.{2})(.{2})","$1-$2-$3 $4:$5:$6");
			break;
		default:
			break;
		}
		return dataString;
	}

}
