package com.parsing.pars.factory.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.parsing.beanFactory.Factory;
import com.parsing.pars.factory.AbstractParsing;

/**
 * 日期格式转换：GMT+0转GMT+8时区
 *
 * param 20180803T073118Z
 * value 20180803153118
 */
@Factory("parseDate")
public class ParseDate extends AbstractParsing{
    @Override
    public String pars(String data) {

        data = data.replaceAll("T", "");
        data = data.replaceAll("Z", "");
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyyMMddHHmmss");
        bjSdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        Date bjDate = null;
        try {
            bjDate = bjSdf.parse(data);
        } catch (ParseException e2) {
            e2.printStackTrace(); 
        }
        bjSdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        SimpleDateFormat bjSdfx = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return bjSdfx.format(bjDate);
    }
}
