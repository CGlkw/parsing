package com.parsing.initialData.factory;


public abstract class AbstractInitailData implements InitialData {

	@Override
	public String initialJsonData(String data) {
		
		return null;
	}
	
	@Override
	public byte[] initialData(String data) {
		
		return null;
	}

	@Override
	public byte[] initialData(byte[] data) {
		
		return null;
	}
	/**
	 * 
	 * @param 33353339
	 * @return 59
	 */
	public byte [] data2ToBytes2(String s) {		
		String replaceAll = s.replaceAll("(.{2})","$1 ").trim();
		return dataToBytes(replaceAll);
	}
	
	/**
	 * 
	 * @param 33 35 33 39
	 * @return 59
	 */
	public byte [] data2ToBytes(String s) {
		String[] hexs = s.trim().split(" ");
		byte[] d = new byte[hexs.length/4];
		for (int i = 0; i < hexs.length; i+=4) {
			char cx = (char) Integer.parseInt((char) Integer.parseInt(hexs[i], 16)+""+(char) Integer.parseInt(hexs[i+1], 16), 16);
			char cy = (char) Integer.parseInt((char) Integer.parseInt(hexs[i+2], 16)+""+(char) Integer.parseInt(hexs[i+3], 16), 16);
			d[i/4] = (byte) (charToByte(cx) << 4 | charToByte(cy));
		}
		return d; 
	}
	
	/**
	 * 
	 * @param 35 39
	 * @return 59
	 */
	public byte [] dataToBytes(String s) {
		String[] hexs = s.trim().split(" ");
		byte[] d = new byte[hexs.length/2];
		for (int i = 0; i < hexs.length; i+=2) {
			char cx = (char) Integer.parseInt(hexs[i], 16);
			char cy = (char) Integer.parseInt(hexs[i+1], 16);
			d[i/2] = (byte) (charToByte(cx) << 4 | charToByte(cy));
		}
		return d;
	}
	
	/**
	 * ascii码解码
	 * 
	 * @param bytes
	 * @param split 如果是true, 每隔两个字符添加空格
	 * @return
	 */
	public static String asciiBytesToString(byte[] bytes, boolean split) {
		StringBuffer sbu = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sbu.append((char) bytes[i]);
			if (split && i % 2 == 1 && i > 0 && i < bytes.length - 1) {
				sbu.append(' ');
			}
		}
		return sbu.toString();
	}
	
	/**
	 * ascii码编码
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] stringToAsciiBytes(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		char[] charArray = str.toCharArray();
		byte[] bytes = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
			bytes[i] = (byte) charArray[i];
		}
		return bytes;
	}
	
    /**
     * 16进制字符串转byte[]
     *
     * @param hexString
     * @return
     */
    public byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    
    public byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
