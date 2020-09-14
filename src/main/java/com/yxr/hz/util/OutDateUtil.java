package com.yxr.hz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OutDateUtil {
    public static String add(String date1,String type) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(date1);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        if(type.equals("月卡")){
            rightNow.add(Calendar.MONTH,1);//日期加10天
            Date dt1=rightNow.getTime();
            String reStr = sdf.format(dt1);
            return reStr;
        }else if(type.equals("季卡")){
            rightNow.add(Calendar.MONTH,3);//日期加10天
            Date dt1=rightNow.getTime();
            String reStr = sdf.format(dt1);
            return reStr;
        }else if(type.equals("半年卡")){
            rightNow.add(Calendar.MONTH,6);//日期加10天
            Date dt1=rightNow.getTime();
            String reStr = sdf.format(dt1);
            return reStr;
        }else if(type.equals("年卡")){
            rightNow.add(Calendar.YEAR,1);
            Date dt1=rightNow.getTime();
            String reStr = sdf.format(dt1);
            return reStr;
        }else {
            return date1;
        }
        }
        public static String back(String date1,String type) throws ParseException {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(date1);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date);
            if(type.equals("月卡")){
                rightNow.add(Calendar.MONTH,-1);//日期加10天
                Date dt1=rightNow.getTime();
                String reStr = sdf.format(dt1);
                return reStr;
            }else if(type.equals("季卡")){
                rightNow.add(Calendar.MONTH,-3);//日期加10天
                Date dt1=rightNow.getTime();
                String reStr = sdf.format(dt1);
                return reStr;
            }else if(type.equals("半年卡")){
                rightNow.add(Calendar.MONTH,-6);//日期加10天
                Date dt1=rightNow.getTime();
                String reStr = sdf.format(dt1);
                return reStr;
            }else if(type.equals("年卡")){
                rightNow.add(Calendar.YEAR,-1);
                Date dt1=rightNow.getTime();
                String reStr = sdf.format(dt1);
                return reStr;
            }else {
                return date1;
            }
        }
}
