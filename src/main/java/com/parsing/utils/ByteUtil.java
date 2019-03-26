package com.parsing.utils;

import java.io.UnsupportedEncodingException;

/**
 * 字节的转换 工具类
 */
public class ByteUtil {
	/**
	 * byte 与 int 的相互转换
	 * 
	 * @param x
	 * @return
	 */
	public static byte intToByte(int x) {
		return (byte) x;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public static int byteToInt(byte b) {
		// Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
		return b & 0xFF;
	}

	/**
	 * 将int数值转换为占两个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytes2ToInt（）配套使用
	 * 
	 * @param value
	 *            要转换的int值
	 * @return byte数组
	 */
	public static byte[] intTo2Bytes(int value) {
		byte[] src = new byte[2];
		src[1] = (byte) ((value >> 8) & 0xFF);
		src[0] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * 将int数值转换为占两个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。 和bytes2ToInt2（）配套使用
	 */
	public static byte[] intTo2Bytes2(int value) {
		byte[] src = new byte[2];
		src[0] = (byte) ((value >> 8) & 0xFF);
		src[1] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytes4ToInt（）配套使用
	 * 
	 * @param value
	 *            要转换的int值
	 * @return byte数组
	 */
	public static byte[] intTo4Bytes(int value) {
		byte[] src = new byte[4];
		src[3] = (byte) ((value >> 24) & 0xFF);
		src[2] = (byte) ((value >> 16) & 0xFF);
		src[1] = (byte) ((value >> 8) & 0xFF);
		src[0] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。 和bytes4ToInt2（）配套使用
	 */
	public static byte[] intTo4Bytes2(int value) {
		byte[] src = new byte[4];
		src[0] = (byte) ((value >> 24) & 0xFF);
		src[1] = (byte) ((value >> 16) & 0xFF);
		src[2] = (byte) ((value >> 8) & 0xFF);
		src[3] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
	 * 
	 * @param src
	 *            byte数组
	 * @param offset
	 *            从数组的第offset位开始
	 * @return int数值
	 */
	public static int bytes2ToInt(byte[] src, int offset) {
		int value;
		value = (int) ((src[offset] & 0xFF) | ((src[offset + 1] & 0xFF) << 8));
		return value;
	}

	/**
	 * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytes2（）配套使用
	 */
	public static int bytes2ToInt2(byte[] src, int offset) {
		int value;
		value = (int) (((src[offset] & 0xFF) << 8) | (src[offset + 1] & 0xFF));
		return value;
	}
	
	/**
	 * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
	 * 
	 * @param src
	 *            byte数组
	 * @param offset
	 *            从数组的第offset位开始
	 * @return int数值
	 */
	public static int bytes4ToInt(byte[] src, int offset) {
		int value;
		value = (int) ((src[offset] & 0xFF) | ((src[offset + 1] & 0xFF) << 8) | ((src[offset + 2] & 0xFF) << 16) | ((src[offset + 3] & 0xFF) << 24));
		return value;
	}

	/**
	 * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytes2（）配套使用
	 */
	public static int bytes4ToInt2(byte[] src, int offset) {
		int value;
		value = (int) (((src[offset] & 0xFF) << 24) | ((src[offset + 1] & 0xFF) << 16) | ((src[offset + 2] & 0xFF) << 8) | (src[offset + 3] & 0xFF));
		return value;
	}

	/**
	 * 将字节数组转换为short类型，即统计字符串长度
	 * 
	 * @param b
	 * @return
	 */
	public static short bytes2Short2(byte[] b) {
		short i = (short) (((b[1] & 0xff) << 8) | b[0] & 0xff);
		return i;
	}

	/**
	 * 将字节数组转换为16进制字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byteToHexString(byte[] bytes) {
		String hexStr = "0123456789ABCDEF";
		String result = "";
		String hex = "";
		for (byte b : bytes) {
			hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
			hex += String.valueOf(hexStr.charAt(b & 0x0F));
			result += hex + " ";
		}
		return result;
	}

	/**
	 * 将16进制字符串转换为字节数组 Convert hex string to byte[]
	 * 
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
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

	/**
	 * 将字节转换为16进制字符串
	 * 
	 * @param byteData
	 * @return
	 */
	public static String byteToHexString(byte byteData) {
		String hexStr = "0123456789ABCDEF";
		String hex = "";
		hex = String.valueOf(hexStr.charAt((byteData & 0xF0) >> 4));
		hex += String.valueOf(hexStr.charAt(byteData & 0x0F));
		String result = hex;
		
		return result;
	}

	/**
	 * 把GBK编码数组 转换成汉字
	 * 
	 * @param byteArray
	 *            GBK编码数组
	 * @return
	 */
	public static String getByteArrayString(byte[][] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			try {
				String chinese = new String(byteArray[i], "GBK");
				sb.append(chinese);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * byte字符串转换为解码后正常字符串
	 * @param bytestr 字符串16进制编码（字符串形式）中间没有空格
	 * @param format 编码格式
	 * @return
	 */
	public static String byteStrToString(String bytestr,String format){
		byte[] bytes = hexStringToBytes(bytestr);
		if(bytes==null){
			return null;
		}
		try {
			return new String(bytes,format);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	public static void main(String[] args) {
		byte[] byteData = { (byte) 0x01, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x39, (byte) 0x85,
				(byte) 0xd8 };
		String str = byteToHexString(byteData);
		System.out.println("byte数组转字符串：\n" + str);

		String strData = "010300000039";
		byte[] or = hexStringToBytes(strData);
		System.out.println("字符串转字节数组 10进制：");
		System.out.println("10进制：");
		for (int i = 0; i < or.length; i++) {
			System.out.print(or[i] + " ");
		}
		System.out.println("\n16进制：");
		for (int i = 0; i < or.length; i++) {
			System.out.print(String.format("0x%02x", or[i]) + " ");
		}

		byte[] perNum = ByteUtil.intTo2Bytes2(60);
		System.out.println("\n10进制：");
		for (int i = 0; i < perNum.length; i++) {
			System.out.print(perNum[i] + " ");
		}
		System.out.println("\n16进制：");
		for (int i = 0; i < perNum.length; i++) {
			System.out.print(String.format("0x%02x", perNum[i]) + " ");
		}
		
		byte b = (byte) 0x72;
		System.out.println("\n" + byteToInt(b));
		
		byte[] by = {(byte) 0x00, (byte) 0x39};
		System.out.println("btye数组转int\n" + bytes2ToInt2(by, 0));
		
		
		byte[] monitorIdByte = new byte[2];
		monitorIdByte[0] = 0x00;
		monitorIdByte[1] = 0x02;
		int temp = ByteUtil.bytes2ToInt2(monitorIdByte, 0);
		System.out.println("temp" + temp);
	}


}