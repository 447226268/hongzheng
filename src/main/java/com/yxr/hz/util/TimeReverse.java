package com.yxr.hz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeReverse {
    public static String timestampToDate(String timestamp){
        if(timestamp == null || timestamp.isEmpty() || timestamp.equals("null")){
            return null;
        }
        String format="yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(timestamp+"000")));
    }
    public static Integer surplus(String date1,String date2) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long startDateTime = dateFormat.parse(date1).getTime();
        long endDateTime = dateFormat.parse(date2).getTime();
        return (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24));

    }
    public static void main(String[] args) throws ParseException {
        System.out.println(timestampToDate(String.valueOf(System.currentTimeMillis()/1000)));
        System.out.println(surplus("2018-5-20","2019-5-20"));
    }
}
