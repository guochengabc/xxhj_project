package com.kongtiaoapp.xxhj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtils {

    /**
     * 定义常量
     **/
    public static final String DATE_JFP_STR = "yyyy-MM";
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";
    public static final String DATE_MONTH = "yyyy-MM";
    public static final String MONTH_DAY_HOUR_MIN = "MM-dd HH:mm";

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {

        return parse(strDate, DATE_FULL_STR);
    }

    /**
     * 使用用户格式提取字符串日�?
     *
     * @param strDate 日期字符�?
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {

        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 取得某日期时间的特定表示格式的字符串
     *
     * @param format 时间格式
     * @param date   日期
     * @return 日期字符串
     */
    public static synchronized String getDate2Str(String format, Date date) {

        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 两个时间比较
     *
     * @param date1
     * @return
     */
    public static int compareDateWithNow(Date date1) {

        Date date2 = new Date();
        int rnum = date1.compareTo(date2);
        return rnum;
    }

    /**
     * 两个时间比较（时间戳）
     *
     * @param date1
     * @return
     */
    public static int compareDateWithNow(long date1) {

        long date2 = dateToUnixTimestamp();
        if (date1 > date2) {
            return 1;
        } else if (date1 < date2) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getNowTime() {

        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
        return df.format(new Date());
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getNowTime(String type) {

        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(new Date());
    }

    /**
     * 获取系统当前时间  年月
     *
     * @return
     */
    public static String getYM() {

        SimpleDateFormat df = new SimpleDateFormat(DATE_JFP_STR);
        return df.format(new Date());
    }

    /**
     * 获取系统当前时间  年月日
     *
     * @return
     */
    public static String getYMD() {

        SimpleDateFormat df = new SimpleDateFormat(DATE_SMALL_STR);
        return df.format(new Date());
    }

    public static String getYMT() {

        SimpleDateFormat df = new SimpleDateFormat(DATE_MONTH);
        return df.format(new Date());
    }

    /**
     * 将指定的日期转换成时间
     *
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date) {

        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(DATE_FULL_STR).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将指定的日期转换成Unix时间�?
     *
     * @param date �?��转换的日�?yyyy-MM-dd
     * @return long 时间�?
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {

        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将当前日期转换成Unix时间戳
     *
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp() {

        long timestamp = new Date().getTime();
        return timestamp;
    }

    /**
     * 将Unix时间戳转换成日期
     *
     * @param timestamp 时间�?
     * @return String 日期字符�?
     */
    public static String unixTimestampToDate(long timestamp) {

        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }

    public static String unixTimestampToDateDay(long timestamp) {

        SimpleDateFormat sd = new SimpleDateFormat(DATE_SMALL_STR);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }

    /**
     * 将Unix时间戳转换成日期 年月格式
     *
     * @param timestamp 时间�?
     * @return String 日期字符�?
     */
    public static String unixTimestampToDateMonth(long timestamp) {

        SimpleDateFormat sd = new SimpleDateFormat(DATE_MONTH);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }

    public static String dateFormatHMS = "HH:mm:ss";

    /**
     * 描述：获取表示当前日期时间的字符串
     *
     * @param format 格式化字符串，如yyyy-MM-dd HH:mm:ss"
     * @return String String类型的当前日期时间
     */
    public static String getCurrentDate(String format) {

        String curDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            Calendar c = new GregorianCalendar();
            curDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curDateTime;
    }

    // 获取开始时间和结束时间

    /**
     * dateCompute(这里用一句话描述这个方法的作用)
     *
     * @param date 基准时间
     * @param tpye 1表示年 2 表示月 3表示周 5表示 日
     * @param time
     * @return
     * @author feiyue
     * @date 2015-6-12 下午3:44:32
     */
    public static long[] dateCompute(Date date, int tpye, int time) {

        long[] l = new long[2];
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(tpye, time);
        l[0] = date.getTime();
        l[1] = c.getTime().getTime();
        return l;
    }

    public static String getTimeStr(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 获取月日小时分钟
     */
    public static String getMonth_Day_Hour_Min(Date date) {
        SimpleDateFormat sdr = new SimpleDateFormat(DATE_FULL_STR);

        return sdr.format(date);

    }



    /**
     * 获取任意时间的下一个月
     * 描述:<描述函数实现的功能>.
     *
     * @param repeatDate
     * @return
     */
    public static String getNextMonth(String repeatDate) {
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(5, 7);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year, month, Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     * 获取任意时间的上一个月
     * 描述:<描述函数实现的功能>.
     *
     * @param repeatDate
     * @return
     */
    public static String getLastMonth(String repeatDate) {
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(5, 7);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year, month - 2, Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }


    /**
     *  
     *      * 获得指定日期的前一天 
     *      *  
     *      * @param specifiedDay 
     *      * @return 
     *      * @throws Exception 
     *      
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {//可以用new Date().toLocalString()传递参数  
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     *  
     *      * 获得指定日期的后一天 
     *      *  
     *      * @param specifiedDay 
     *      * @return 
     *      
     */
    public static String getSpecifiedDayAfter(String specifiedDay)

    {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }
}


