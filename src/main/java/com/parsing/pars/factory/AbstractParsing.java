package com.parsing.pars.factory;

import java.util.Arrays;

public abstract class AbstractParsing implements Parsing{
	@Override
	public Object pars(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object pars(byte[] data, int index, int offset) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object pars(String data) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 判断数组是否全部为零
	 * @param data
	 * @return boolean
	 */
	public boolean isAllZero(byte[] data) {
		for (byte b : data) {
			if (b!=0) 
				return false;
		}
		return true;
	}
	/**
	 * 去除数组前后端的0
	 * @param  data
	 * @return data
	 */
    public byte[] trim(byte[] data) {
        int len = data.length;
        int st = 0;

        while ((st < len) && (data[st] == 0)) {
            st++;
        }
        while ((st < len) && (data[len - 1] == 0)) {
            len--;
        }
        
        return ((st > 0) || (len < data.length)) ? Arrays.copyOfRange(data, st, len) : data;
    }
}
