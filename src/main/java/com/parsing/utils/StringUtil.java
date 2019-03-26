package com.parsing.utils;

public class StringUtil {
    /**
     * 统计作为字节 byte2 在byte1 中的个数(不通用，慎用)
     * @param byte1
     * @param byte2
     * @return
     */
    public static int countStrAsByte(String byte1, String byte2){
        int counter = 0;
        int index = byte1.indexOf(byte2);
        if (index == -1) {
            return 0;
        }

        while (index!=-1) {
            if (index%2==0){
                counter++;
                byte1 = byte1.substring(index + byte2.length());
            }else{
                byte1 = byte1.substring(index + byte2.length()-1);
            }

            index = byte1.indexOf(byte2);
        }
        return counter;
    }
}
