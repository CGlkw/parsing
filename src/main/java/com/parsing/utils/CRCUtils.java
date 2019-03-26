package com.parsing.utils;

import java.util.zip.CRC32;

public class CRCUtils {

	/**
	 * CRC32 字节校验
	 */
	public static String crc32(byte[] bytes) {
		CRC32 crc32 = new CRC32();
		crc32.update(bytes);
		return Long.toHexString(crc32.getValue()).toUpperCase();
	}

	/**
	 * CRC16 字节校验
	 */
	public static String crc16(byte[] data) {
		byte[] buf = new byte[data.length];// 存储需要产生校验码的数据  
		for (int i = 0; i < data.length; i++) {
			buf[i] = data[i];
		}
		int len = buf.length;
		int crc = 0xFFFF;//16位  
		for (int pos = 0; pos < len; pos++) {
			if (buf[pos] < 0) {
				crc ^= (int) buf[pos] + 256; // XOR byte into least sig. byte of  
													  // crc  
			} else {
				crc ^= (int) buf[pos]; // XOR byte into least sig. byte of crc  
			}
			for (int i = 8; i != 0; i--) { // Loop over each bit  
				if ((crc & 0x0001) != 0) { // If the LSB is set  
					crc >>= 1; // Shift right and XOR 0xA001  
					crc ^= 0xA001;
				} else
					// Else LSB is not set  
					crc >>= 1; // Just shift right  
			}
		}
		String c = Integer.toHexString(crc);
		if (c.length() == 4) {
			c = c.substring(2, 4) + c.substring(0, 2);
		} else if (c.length() == 3) {
			c = "0" + c;
			c = c.substring(2, 4) + c.substring(0, 2);
		} else if (c.length() == 2) {
			c = "0" + c.substring(1, 2) + "0" + c.substring(0, 1);
		}
		return c.toUpperCase();
	}
	private static final int POLYNOMIAL = /*0xA053;*//*0x0589*/0xA001;
    /*    02 05 00 03 FF 00 的不同crc计算值：
    CRC-16 0x127C
    CRC-16 (Modbus)    0x097C 对应的多项式为 0xA001
    CRC-16 (Sick)  0xE2F0
    CRC-CCITT (XModem) 0xF2B8
    CRC-CCITT (0xFFFF) 0xFCA8
    CRC-CCITT (0x1D0F) 0xC386
    CRC-CCITT (Kermit) 0xA63E
    CRC-DNP    0x6E28*/
    private static final int PRESET_VALUE = 0xFFFF;

    public static String getHexString(int i){
        String hexString = String.format("%04x",i);
        return hexString;
    }

    /**
     * @param data byte[]
     * @return 低位在前，高位在后的16进制字符串
     */
    public static String getCrc16String(byte[] data){
        StringBuilder builder = new StringBuilder();
        String hex = getHexString(getCrc16(data));
        builder.append(hex.substring(2));
        builder.append(hex.substring(0,2));
        return builder.toString();
    }

    /**
     * @param data String
     * @return 低位在前，高位在后的16进制字符串
     */
    public static String getCrc16String(String data){
        StringBuilder builder = new StringBuilder();
        String hex = getHexString(getCrc16(data));
        builder.append(hex.substring(2));
        builder.append(hex.substring(0,2));
        return builder.toString();
    }
    /**
     * @param data byte数组
     * @return  CRC16校验得到的十进制int
     */
    public static int getCrc16(byte[] data){
       // System.out.println("\nCRC 16 calculation progress:\n");
        int current_crc_value = PRESET_VALUE;
        for (int i = 0; i < data.length; i++){
            current_crc_value ^= data[i] & 0xFF;
            for (int j = 0; j < 8; j++ ) {
                if ((current_crc_value & 1) != 0) {
                    current_crc_value = (current_crc_value >>> 1) ^ POLYNOMIAL;
                }
                else{
                    current_crc_value = current_crc_value >>> 1;
                }
            }
        }
        return current_crc_value & 0xFFFF;
    }

    /**
     * @param str 16进制字符串
     * @return CRC16校验得到的十进制int
     */
    public static int getCrc16(String str){
        byte[] data = hexStringToBytes(str);
       // System.out.println("\nCRC 16 calculation progress:\n");
        int current_crc_value = PRESET_VALUE;
        for (int i = 0; i < data.length; i++){
            current_crc_value ^= data[i] & 0xFF;
            for (int j = 0; j < 8; j++ ) {
                if ((current_crc_value & 1) != 0) {
                    current_crc_value = (current_crc_value >>> 1) ^ POLYNOMIAL;
                }
                else{
                    current_crc_value = current_crc_value >>> 1;
                }
            }
        }
        return current_crc_value & 0xFFFF;
    }
    /**
     * byte[] 转 16进制字符
     *
     * @param b
     * @return
     */
    public static String printHexString(byte[] b) {
        StringBuffer returnValue = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            if (i % 2 == 0) {
                returnValue.append(hex.toUpperCase() + "");
            } else {
                returnValue.append(hex.toUpperCase() + " ");
            }
        }

        return returnValue.toString();
    }
    /**
     * 16进制字符串转byte[]
     *
     * @param hexString
     * @return
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
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
