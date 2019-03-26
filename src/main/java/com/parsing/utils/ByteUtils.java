package com.parsing.utils;

import java.io.UnsupportedEncodingException;

/**
 * 字节的转换 工具类
 */
public class ByteUtils {

	public static byte intToByte(int x) {
		return (byte) x;
	}

	public static int byteToInt(byte b) {
		// Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
		return b & 0xFF;
	}

	/**
	 * int => byte[len], 低位在前, 高位在后, 不足补0
	 * 反转: toInt()
	 */
	public static byte[] toByteArray(int iSource, int iArrayLen) {
		byte[] bLocalArr = new byte[iArrayLen];
		for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
			bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);
		}
		return bLocalArr;
	}

	/**
	 * byte[len] => int, 低位在前, 高位在后
	 * 反转: toByteArray()
	 */
	public static int toInt(byte[] byteArray, int offset) {
		int iOutcome = 0;
		byte bLoop;

		for (int i = 0; i < byteArray.length - offset; i++) {
			bLoop = byteArray[i + offset];
			iOutcome += (bLoop & 0xFF) << (8 * i);
		}
		return iOutcome;
	}

	/**
	 * int => byte[len], 高位在前, 低位在后, 不足补0
	 * 反转: toInt2()
	 */
	public static byte[] toByteArray2(int iSource, int iArrayLen) {
		byte[] bLocalArr = new byte[iArrayLen];
		for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
			bLocalArr[iArrayLen - 1 - i] = (byte) (iSource >> 8 * i & 0xFF);
		}
		return bLocalArr;
	}

	/**
	 * byte[len] => int, 高位在前, 低位在后
	 * 反转: toByteArray2()
	 */
	public static int toInt2(byte[] byteArray, int offset) {
		int iOutcome = 0;
		byte bLoop;

		for (int i = 0; i < byteArray.length - offset; i++) {
			bLoop = byteArray[byteArray.length - 1 - i];
			iOutcome += (bLoop & 0xFF) << (8 * i);
		}
		return iOutcome;
	}

	/**
	 * int => byte[2], 低位在前，高位在后
	 * 反转: bytes2ToInt()
	 */
	public static byte[] intTo2Bytes(int value) {
		return new byte[] { //
				(byte) (value & 0xFF), //
				(byte) ((value >> 8) & 0xFF) //
		};
	}

	/**
	 * byte[2] => int, 低位在前，高位在后
	 * 反转: intTo2Bytes()
	 */
	public static int bytes2ToInt(byte[] src, int offset) {
		return (int) ( //
		(src[offset] & 0xFF) //
				| ((src[offset + 1] & 0xFF) << 8) //
		);
	}

	/**
	 * int => byte[2], 高位在前，低位在后
	 * 反转: bytes2ToInt2()
	 */
	public static byte[] intTo2Bytes2(int value) {
		return new byte[] { //
				(byte) ((value >> 8) & 0xFF), //
				(byte) (value & 0xFF) //
		};
	}

	/**
	 * byte[2] => int, 高位在前，低位在后
	 * 反转: intTo2Bytes2()
	 */
	public static int bytes2ToInt2(byte[] src, int offset) {
		return (int) ( //
		((src[offset] & 0xFF) << 8) //
				| (src[offset + 1] & 0xFF) //
		);
	}

	/**
	 * int => byte[4], 低位在前，高位在后
	 * 反转: bytes4ToInt()
	 */
	public static byte[] intTo4Bytes(int value) {
		return new byte[] { //
				(byte) (value & 0xFF), //
				(byte) ((value >> 8) & 0xFF), //
				(byte) ((value >> 16) & 0xFF), //
				(byte) ((value >> 24) & 0xFF), //
		};
	}

	/**
	 * byte[4] => int, 低位在前，高位在后
	 * 反转: intTo4Bytes()
	 */
	public static int bytes4ToInt(byte[] src, int offset) {
		return (int) ( //
		(src[offset] & 0xFF) //
				| ((src[offset + 1] & 0xFF) << 8) //
				| ((src[offset + 2] & 0xFF) << 16) //
				| ((src[offset + 3] & 0xFF) << 24) //
		);
	}

	/**
	 * int => byte[4], 高位在前，低位在后
	 * 反转: bytes4ToInt2()
	 */
	public static byte[] intTo4Bytes2(int value) {
		return new byte[] { //
				(byte) ((value >> 24) & 0xFF), //
				(byte) ((value >> 16) & 0xFF), //
				(byte) ((value >> 8) & 0xFF), //
				(byte) (value & 0xFF) //
		};
	}

	/**
	 * byte[4] => int, 高位在前，低位在后
	 * 反转: intTo4Bytes2()
	 */
	public static int bytes4ToInt2(byte[] src, int offset) {
		return (int) ( //
		((src[offset] & 0xFF) << 24) //
				| ((src[offset + 1] & 0xFF) << 16) //
				| ((src[offset + 2] & 0xFF) << 8) //
				| (src[offset + 3] & 0xFF) //
		);
	}

	/**
	 * byte => Hex
	 */
	public static String byteToHexString(byte bte) {
		String hexStr = "0123456789ABCDEF";
		StringBuffer sb = new StringBuffer() //
				.append(hexStr.charAt((bte & 0xF0) >> 4)) //
				.append(hexStr.charAt(bte & 0x0F));
		return sb.toString();
	}

	/**
	 * byte[] => Hex, 空格分隔
	 */
	public static String bytesToHexString(byte[] bytes) {
		String hexStr = "0123456789ABCDEF";
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes) {
			sb.append(String.valueOf(hexStr.charAt((b & 0xF0) >> 4)));
			sb.append(String.valueOf(hexStr.charAt(b & 0x0F)));
			sb.append(' ');
		}
		return sb.toString().trim();
	}

	/**
	 * Hex => byte[], 空格分隔
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
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
	 * byte[][] => 汉字, GBK编码
	 */
	public static String getByteArrayString(byte[][] byteArray) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			String chinese = new String(byteArray[i], "GBK");
			sb.append(chinese);
		}
		return sb.toString();
	}

	/**
	 * char => byte
	 */
	public static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * 字节ascii码是否大小写字母
	 */
	public static boolean isEnglishLetter(byte bte) {
		return (bte >= 65 && bte <= 90) || (bte >= 97 && bte <= 122);
	}

	/**
	 * 字节ascii码是否大小写字母或数字
	 */
	public static boolean isLetterOrNum(byte bte) {
		return (bte >= 48 && bte <= 57) || (bte >= 65 && bte <= 90) || (bte >= 97 && bte <= 122);
	}

	/**
	 * byte[4] => float, 高位在前, 低位在后
	 */
	public static float bytes4ToFloat2(byte[] arr, int offset) {
		return Float.intBitsToFloat(bytes4ToInt2(arr, offset));
	}

	/**
	 * byte[4] => float, 低位在前, 高位在后
	 */
	public static float bytes4ToFloat(byte[] arr, int offset) {
		byte b0 = arr[2];
		byte b1 = arr[3];
		arr[2] = arr[0];
		arr[3] = arr[1];
		arr[0] = b0;
		arr[1] = b1;
		return Float.intBitsToFloat(bytes4ToInt2(arr, offset));
	}

	/**
	 * double => byte[8], 高位在前, 低位在后(大端)
	 */
	public static byte[] double2Bytes(double d) {
		long value = Double.doubleToRawLongBits(d);
		byte[] byteRet = new byte[8];
		for (int i = 0; i < 8; i++) {
			byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
		}
		return byteRet;
	}

	/**
	 * byte[8] => double, 高位在前, 低位在后(大端)
	 */
	public static double bytes2Double(byte[] arr) {
		long value = 0;
		for (int i = 0; i < 8; i++) {
			value |= ((long) (arr[i] & 0xff)) << (8 * i);
		}
		return Double.longBitsToDouble(value);
	}

	public static String hexDateBytesToString (byte[] data) {
		String s = "20";
		for (byte b : data) {			
			s += String.format("%02d", b);
		}
		return s;
	}
	
}