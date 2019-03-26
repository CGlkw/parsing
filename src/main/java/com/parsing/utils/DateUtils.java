package com.parsing.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static  Calendar date = Calendar.getInstance();
    /**
     * 格式化字符串为日期时间
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr){
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String dateStr,String fortmat){
        try {
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(fortmat);
            return simpleDateFormat1.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatDate(Date date){
        try {
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSysYear() {
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }
}
