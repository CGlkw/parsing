package com.parsing.utils;

public class AsciiUtils {

	/**
	 * 16进制数字 转 16进制ascii码
	 * 
	 * @param value 16进制数字, 例: [5900]
	 * @return 16进制ascii码, 例: [35 39 30 30]
	 */
	public static String stringToAscii(String value, boolean split) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			sbu.append(Integer.toHexString((int) chars[i]));
			if (split) {
				sbu.append(" ");
			}
		}
		if (split) {
			return sbu.deleteCharAt(sbu.length() - 1).toString();
		}
		return sbu.toString();
	}

	/**
	 * 16进制ascii码解码
	 * 
	 * @param value 16进制ascii码, 例: [35 39 30 30]
	 * @return 解码结果, 例: [5900]
	 */
	public static String asciiToString(String value) {
		if (value == null || "".equals(value.trim())) {
			return ""; 
		}
		StringBuffer sbu = new StringBuffer();
		String[] hexs = value.trim().split(" ");
		for (int i = 0; i < hexs.length; i++) {
			sbu.append((char) Integer.parseInt(hexs[i], 16));
		}
		return sbu.toString();
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

}
