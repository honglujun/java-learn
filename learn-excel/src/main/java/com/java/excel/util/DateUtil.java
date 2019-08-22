package com.java.excel.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * 此类描述的是：日期工具类
 *
 * @Date: 2019年1月10日 上午10:23:33
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 默认日期格式：yyyy-MM-dd
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 日期格式：yyyyMMdd
     */
    public static final String DATE_PATTERN_YYYYMMDD = "yyyyMMdd";

    /**
     * 日期格式：yyyyMMddhhmmssSSS
     */
    public static final String DATE_PATTERN_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * 日期格式：yyyyMMddhhmmss
     */
    public static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 默认时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认时间戳格式，到毫秒 yyyy-MM-dd HH:mm:ss SSS
     */
    public static final String DEFAULT_DATEDETAIL_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    /**
     * 1天折算成毫秒数
     */
    public static final long MILLIS_A_DAY = 24 * 3600 * 1000;

    public static final String TIMEZONE = "GMT+08:00";

    @SuppressWarnings("rawtypes")
    private static HashMap parsers = new HashMap();

    @SuppressWarnings("unchecked")
    private static SimpleDateFormat getDateParser(String pattern) {
        Object parser = parsers.get(pattern);
        if (parser == null) {
            parser = new SimpleDateFormat(pattern);
            parsers.put(pattern, parser);
        }
        return (SimpleDateFormat) parser;
    }

    /**
     * 取得当前系统日期
     *
     * @return
     */
    public static Date currentDate() {
        return new Date();
    }

    /**
     * 取得系统当前日期，返回默认日期格式的字符串。
     *
     * @param strFormat
     * @return
     */
    public static String nowDate(String strFormat) {
        Date date = new Date();
        return getDateParser(strFormat).format(date);
    }

    /**
     * 取得当前系统时间戳
     *
     * @return
     */
    public static Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 将日期字符串转换为java.util.Date对象
     *
     * @param dateString
     * @param pattern    日期格式
     * @return
     * @throws Exception
     */
    public static Date toDate(String dateString, String pattern) {
        DateFormat dataformat = new SimpleDateFormat(pattern);
        dataformat.setLenient(false);
        try {
            return dataformat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(dateString + "不能转换成" + pattern);
        }
    }

    /**
     * 将日期字符串转换为java.util.Date对象，使用默认日期格式
     *
     * @param dateString
     * @return
     * @throws Exception
     */
    public static Date toDate(String dateString) throws Exception {
        return getDateParser(DEFAULT_DATE_PATTERN).parse(dateString);
    }

    /**
     * 将时间字符串转换为java.util.Date对象
     *
     * @param dateString
     * @return
     * @throws Exception
     */
    public static Date toDateTime(String dateString) throws Exception {
        return getDateParser(DEFAULT_DATETIME_PATTERN).parse(dateString);
    }

    /**
     * 将java.util.Date对象转换为字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String toDateString(Date date, String pattern) {
        return getDateParser(pattern).format(date);
    }

    /**
     * 将java.util.Date对象转换为字符串，使用默认日期格式
     *
     * @param date
     * @return
     */
    public static String toDateString(Date date) {
        return getDateParser(DEFAULT_DATE_PATTERN).format(date);
    }

    /**
     * 将java.util.Date对象转换为时间字符串，使用默认日期格式
     *
     * @param date
     * @return
     */
    public static String toDateTimeString(Date date) {
        return getDateParser(DEFAULT_DATETIME_PATTERN).format(date);
    }

    /**
     * 将java.util.Date对象转换为时间字符串，使用默认日期格式
     *
     * @param date
     * @return
     */
    public static String toDateTimeString2(Date date) {
        return getDateParser(DEFAULT_DATEDETAIL_PATTERN).format(date);
    }

    /**
     * 日期相减
     *
     * @param date
     * @param day
     * @return
     */
    public static Date diffDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) - ((long) day) * MILLIS_A_DAY);
        return c.getTime();
    }

    /**
     * 返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 日期相加
     *
     * @param date 日期
     * @param day  天数
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, int day) {
        Calendar c = Calendar.getInstance();

        c.setTimeInMillis(getMillis(date) + ((long) day) * MILLIS_A_DAY);
        return c.getTime();
    }

    /**
     * @param yyyyMM
     * @return 该年月最后一天 例如传入200502，返回 Date:2005-02-28
     */
    public static Date getMonthLastDay(String yyyyMM) {
        if (yyyyMM == null || yyyyMM.length() != 6) {
            throw new RuntimeException("传入格式不对!");
        }

        int year = Integer.parseInt(yyyyMM.substring(0, 4));
        int month = Integer.parseInt(yyyyMM.substring(4, 6));

        // Date date = new Date(year,month,1);
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month - 1, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        //
        // cal.set(Calendar.HOUR,0);
        // cal.set(Calendar.MINUTE,0);
        // cal.set(Calendar.SECOND,0);

        return cal.getTime();
    }

    /**
     * 取得传入年月 该月的第一天 例如 传入200505 返回2005-05-01
     */
    public static Date getMonthFirstDay(String yearMonth) {
        if (yearMonth == null || yearMonth.length() != 6) {
            throw new RuntimeException("传入年月参数错误");
        }
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Integer.parseInt(yearMonth.substring(0, 4)), Integer.parseInt(yearMonth.substring(4, 6)) - 1, 1);

        Date retDate = cal.getTime();
        return retDate;

    }

    public static Date addMonth(Date dt, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + month);
        return cal.getTime();
    }

    public static Date addYear(Date dt, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + year);
        return cal.getTime();
    }

    public static Date getLastSecond(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * date类型转换为String类型
     *
     * @param date
     * @param formatType
     * @return
     */
    public static String dateToString(Date date, String formatType) {

        return new SimpleDateFormat(formatType).format(date);
    }

    /**
     * string类型转换为date类型
     *
     * @param dateStr
     * @param formatType
     * @return
     */
    public static Date stringToDate(String dateStr, String formatType) {

        try {
            return new SimpleDateFormat(formatType).parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * long转换为Date类型
     *
     * @param currentTime
     * @param formatType
     * @return
     */
    public static Date longToDate(long currentTime, String formatType) throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    /**
     * long类型转换为String类型
     *
     * @param currentTime
     * @param formatType
     * @return
     */
    public static String longToString(long currentTime, String formatType) throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    /**
     * string类型转换为long类型
     *
     * @param strTime
     * @param formatType
     * @return
     */
    public static long stringToLong(String strTime, String formatType) throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    /**
     * date类型转换为long类型
     *
     * @param date
     * @return
     */
    public static Long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 当月最后一天
     *
     * @return
     */
    public static String getLastDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN_YYYYMMDD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1); // 设置为该月第一天
        calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        return day_last;
    }

    /**
     * 当前时间加分钟
     *
     * @param minutes 分钟数
     * @return
     */
    public static Date addMinutes(int minutes) {
        return org.apache.commons.lang3.time.DateUtils.addMinutes(new Date(), minutes);
    }

    /**
     * 根据传入表达式计算下次时间
     *
     * @param timeoutExpress
     * @return
     */
    public static Date nextTime(String timeoutExpress) {

        Integer value = Integer.valueOf(timeoutExpress.substring(0, timeoutExpress.length() - 1));
        String unit = timeoutExpress.substring(timeoutExpress.length() - 1);

        Calendar calendar = Calendar.getInstance();

        switch (unit) {
            case "m":
                calendar.add(Calendar.MINUTE, value);
                break;
            case "h":
                calendar.add(Calendar.HOUR_OF_DAY, value);
                break;
            case "d":
                calendar.add(Calendar.DATE, value);
                break;
            case "s":
                calendar.add(Calendar.SECOND, value);
                break;
            default:
                calendar.add(Calendar.DATE, 1);
        }

        return calendar.getTime();
    }

    public static Date addDay(Date date, int day) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, day);
    }

    /**
     * 是否是同一天
     *
     * @param date1
     * @param date2
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        if (StringUtils.equals(toDateString(date1, DATE_PATTERN_YYYYMMDD), toDateString(date2, DATE_PATTERN_YYYYMMDD))) {
            return true;
        }

        return false;
    }

    /**
     * 获取退款下一次执行时间
     *
     * @return
     */
    public static Date getNextDateTime() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR_OF_DAY, 2);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            logger.info("退款时间设为下一天");
            return cal.getTime();
        } catch (Exception e) {
            logger.error("获取退款NEXT_TIME 异常 ");
            return Calendar.getInstance().getTime();
        }
    }

    /**
     * 设置下次执行时间
     *
     * @return
     */
    public static Date getNextDateTime(int days, int hours, int minutes, int seconds) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, days);
            cal.set(Calendar.HOUR_OF_DAY, hours);
            cal.set(Calendar.MINUTE, minutes);
            cal.set(Calendar.SECOND, seconds);
            return cal.getTime();
        } catch (Exception e) {
            logger.error("获取退款NEXT_TIME 异常 ");
            return Calendar.getInstance().getTime();
        }
    }

    /**
     * 判断当前时间是否在0点到3点之间
     *
     * @return
     */
    public static boolean isBetweenZeroAndThirdClock(Date date) {
        if (null == date) {
            return false;
        }

        String hourStr = toDateString(date, DATE_PATTERN_YYYYMMDDHHMMSS).substring(8, 10);
        // 是否在0点到3点之间
        if (Integer.parseInt(hourStr) >= 0 && Integer.parseInt(hourStr) < 3) {
            return true;
        }
        return false;
    }

    /**
     * 获取某个日期（someDay）n 天后的日期
     *
     * @param date
     * @param n
     * @param format
     * @return
     */
    public static Date getSomeDayAfterNDay(Date date, int n, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, n);
        return stringToDate(sdf.format(cal.getTime()), DEFAULT_DATE_PATTERN);
    }

    /**
     * 获取当前时间
     *
     * @param format
     * @return
     */
    public static Date getNowTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
        cal.setTime(new Date());
        return stringToDate(sdf.format(cal.getTime()), DEFAULT_DATE_PATTERN);
    }

    public final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 计算指定时间所在自然周的开始时间
     *
     * @param dateTime
     * @return
     */
    public static Date getWeekStartTime(Date dateTime) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
        cal.setTime(dateTime);
        int weekday = cal.get(Calendar.DAY_OF_WEEK) - 2;
        cal.add(Calendar.DATE, -weekday);
        try {
            cal.setTime(longSdf.parse(shortSdf.format(cal.getTime()) + " 00:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal.getTime();
    }

    /**
     * 获取当前自然周weekid，以当前周开始时间的时间戳为weekid
     *
     * @return
     */
    public static String getWeekId() {
        Date nowTime = getNowTime("yyyy-MM-dd HH:mm:ss");
        Date weekStartTime = getWeekStartTime(nowTime);
        return String.valueOf(weekStartTime.getTime());
    }

    /**
     *  得到某一天是这一年的第几周
     *       
     *  @param date
     *  @return
     *      
     */
    public static int getWeek(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            cal.setTime(format.parse(date));
            cal.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    /**
     * 获取上一周的时间区间
     *
     * @return
     */
    public static String getLastTimeInterval(String dateformat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 1 - dayOfWeek;
        int offset2 = 7 - dayOfWeek;
        calendar1.add(Calendar.DATE, offset1 - 7);
        calendar2.add(Calendar.DATE, offset2 - 7);
        String lastBeginDate = sdf.format(calendar1.getTime());
        String lastEndDate = sdf.format(calendar2.getTime());
        return lastBeginDate + "-" + lastEndDate;
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {//可以用new Date().toLocalString()传递参数
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

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
                .getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
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

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayAfter;
    }
}


