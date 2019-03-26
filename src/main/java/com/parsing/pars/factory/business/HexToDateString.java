package com.parsing.pars.factory.business;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;
import com.parsing.utils.ByteUtils;

@Factory("hexToDate")
public class HexToDateString extends AbstractParsing {

	@Override
	public String pars(byte[] data) {
		int len = data.length;
		String dataString = null;
		switch (len) {
		case 3:
			dataString = autoGenericCode(data[0], 2)+":"+autoGenericCode(data[1], 2)+":"+autoGenericCode(data[2], 2);
			break; 
		case 6:
			dataString= "20"+autoGenericCode(data[0], 2)+"-"+autoGenericCode(data[1], 2)+"-"+autoGenericCode(data[2], 2)
				+" "+autoGenericCode(data[3], 2)+":"+autoGenericCode(data[4], 2)+":"+autoGenericCode(data[5], 2);
			break;
		case 7:
			dataString= autoGenericCode(data[0], 2)+autoGenericCode(data[1], 2)+"-"+autoGenericCode(data[2], 2)+"-"+autoGenericCode(data[3], 2)
			+" "+autoGenericCode(data[4], 2)+"-"+autoGenericCode(data[5], 2)+"-"+autoGenericCode(data[6], 2);
			break;
		default:
			break;
		}
		return dataString;
	}

	public static String autoGenericCode(byte code, int num) {
		String result = "";
		result = String.format("%0" + num + "d", (int)code);

		return result;
	}
}
