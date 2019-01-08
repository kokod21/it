package com.koko.it.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeUtils {

    public static final String  TODAY_DATE = "yyyy-MM-dd";
    public static final String  TODAY_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String  TODAY_MINUTE = "yyyy-MM-dd HH:mm";

    public static final String  TODAY_H_M = "HH:mm";

    public static String longToString(long l, String formatString) {
        //new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        return format.format(new Date(l));
    }

    public static String todayToString(String formatString) {
        //new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        return format.format(new Date(System.currentTimeMillis()));
    }


    public static long dateToLong(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime().getTime();
    }


    public static long stringToLong(String string, String formatString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
            Date date = simpleDateFormat.parse(string);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isBeforeToday(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date date1 = calendar.getTime();
        Date date2 = new Date(System.currentTimeMillis());
        return date1.before(date2);
    }


    public static boolean isBeforeToday(long time) {
        return time < System.currentTimeMillis();

    }

    public static String secondToMinute(String time){
        if(TextUtils.isEmpty(time)){
            return time;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }

    /**
     * 比较time1是否大于time2
     * @param time1
     * @param time2
     * @return
     */
    public static boolean checkTimeIsBigger(String time1, String time2){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date d1 = sdf.parse(time1);
            Date d2 = sdf.parse(time2);
            return d1.getTime() >= d2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
