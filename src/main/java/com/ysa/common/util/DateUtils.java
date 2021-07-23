package com.ysa.common.util;

import com.ysa.common.enums.DatePatternEnum;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.ysa.common.enums.DatePatternEnum.NORM_DATETIME_PATTERN;

/**
 * 时间工具类
 *
 * @author ysa
 * @date 2019/1/29 4:29 PM
 */
@NoArgsConstructor
public final class DateUtils {

    /**
     * GMT ZoneId
     */
    private static final ZoneId GMT_ZONE_ID = ZoneId.of("GMT");

    /**
     * get current unix time
     *
     * @return return current unix time
     */
    public static int nowUnix() {
        return (int) Instant.now().getEpochSecond();
    }

    /**
     * format unix time to string
     *
     * @param unixTime unix time
     * @param pattern  date format pattern
     * @return return string date
     */
    public static String toString(long unixTime, String pattern) {
        return Instant.ofEpochSecond(unixTime).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * format unix time to string
     *
     * @param unixTime    unix time
     * @param patternEnum pattern
     * @return return string date
     */
    public static String toString(long unixTime, DatePatternEnum patternEnum) {
        return toString(unixTime, patternEnum.getDatePattern());
    }

    /**
     * format date to string
     *
     * @param date    date instance
     * @param pattern date format pattern
     * @return return string date
     */
    public static String toString(Date date, String pattern) {
        Instant instant = new Date((date.getTime())).toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * format date to string
     *
     * @param date        date instance
     * @param patternEnum date format pattern
     * @return return string date
     */
    public static String toString(Date date, DatePatternEnum patternEnum) {
        return toString(date, patternEnum.getDatePattern());
    }

    /**
     * 获取时间的指定格式字符串
     *
     * @param date    时间
     * @param pattern 时间转字符串所需格式
     * @return string date
     */
    public static String toString(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取时间的指定格式字符串
     *
     * @param date        时间
     * @param patternEnum 时间转字符串所需格式
     * @return string date
     */
    public static String toString(LocalDate date, DatePatternEnum patternEnum) {
        return toString(date, patternEnum.getDatePattern());
    }

    /**
     * 获取时间的指定格式字符串
     *
     * @param date    时间
     * @param pattern 时间转字符串所需格式
     * @return string date
     */
    public static String toString(LocalDateTime date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取时间的指定格式字符串
     *
     * @param date        时间
     * @param patternEnum 时间转字符串所需格式
     * @return string date
     */
    public static String toString(LocalDateTime date, DatePatternEnum patternEnum) {
        return toString(date, patternEnum.getDatePattern());
    }

    /**
     * 获取时间的默认格式字符串
     * "2019-10-10 13:23:23"
     *
     * @param date 时间
     * @return string date
     */
    public static String toString(Date date) {
        return toString(date, NORM_DATETIME_PATTERN);
    }

    /**
     * 获取时间的默认格式字符串
     * "2019-10-10 13:23:23"
     *
     * @param time 时间
     * @return string date
     */
    public static String toString(LocalDateTime time) {
        return toString(time, NORM_DATETIME_PATTERN);
    }

    /**
     * format string time to unix time
     *
     * @param time    string date
     * @param pattern date format pattern
     * @return return unix time
     */
    public static int toUnix(String time, String pattern) {
        LocalDateTime formatted = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
        return (int) formatted.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * format string time to unix time
     *
     * @param time        string date
     * @param patternEnum date format pattern
     * @return return unix time
     */
    public static int toUnix(String time, DatePatternEnum patternEnum) {
        return toUnix(time, patternEnum.getDatePattern());
    }

    /**
     * format string (yyyy-MM-dd HH:mm:ss) to unix time
     *
     * @param time string datetime
     * @return return unix time
     */
    public static int toUnix(String time) {
        return toUnix(time, NORM_DATETIME_PATTERN);
    }

    /**
     * 时间转时间戳
     *
     * @param date Date类型的时间
     * @return unix time
     */
    public static int toUnix(Date date) {
        return (int) date.toInstant().getEpochSecond();
    }

    /**
     * 字符串格式时间以pattern时间格式转为Date类型
     *
     * @param time    string time
     * @param pattern 时间格式 例如 yyyy:MM:dd HH:mm:ss
     * @return Date 类型的时间
     */
    public static Date toDate(String time, String pattern) {
        return toDateTime(time, pattern);
    }

    /**
     * 字符串格式时间以pattern时间格式转为Date类型
     *
     * @param time            string time
     * @param datePatternEnum 时间格式 例如 yyyy:MM:dd HH:mm:ss
     * @return Date 类型的时间
     */
    public static Date toDate(String time, DatePatternEnum datePatternEnum) {
        return toDate(time, datePatternEnum.getDatePattern());
    }

    /**
     * unix time to Date time
     *
     * @param unixTime unix time
     * @return Date 类型的时间
     */
    public static Date toDate(long unixTime) {
        return Date.from(Instant.ofEpochSecond(unixTime));
    }

    /**
     * LocalDateTime格式转为Date 格式的时间
     *
     * @param localDateTime LocalDateTime 格式的时间
     * @return Date格式的时间
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 字符串格式时间以pattern时间格式转为Date类型
     *
     * @param time    string time
     * @param pattern 时间格式 例如 yyyy:MM:dd HH:mm:ss
     * @return Date 类型的时间
     */
    public static Date toDateTime(String time, String pattern) {
        LocalDateTime formatted = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
        return Date.from(formatted.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 字符串格式时间以pattern时间格式转为Date类型
     *
     * @param time            string time
     * @param datePatternEnum 时间格式 例如 yyyy:MM:dd HH:mm:ss
     * @return Date 类型的时间
     */
    public static Date toDateTime(String time, DatePatternEnum datePatternEnum) {
        return toDateTime(time, datePatternEnum.getDatePattern());
    }

    /**
     * 字符串格式时间以pattern时间格式转为LocalDateTime类型
     *
     * @param time    string time
     * @param pattern 时间格式 例如 yyyy:MM:dd HH:mm:ss
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(String time, String pattern) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串格式时间以pattern时间格式转为LocalDateTime类型
     *
     * @param time            string time
     * @param datePatternEnum 时间格式 例如 yyyy:MM:dd HH:mm:ss
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(String time, DatePatternEnum datePatternEnum) {
        return toLocalDateTime(time, datePatternEnum.getDatePattern());
    }

    /**
     * 字符串格式时间以默认时间格式转为LocalDateTime类型
     *
     * @param time 默认时间格式(yyyy:MM:dd HH:mm:ss)的string time
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(String time) {
        return toLocalDateTime(time, NORM_DATETIME_PATTERN);
    }

    /**
     * Date格式转为LocalDateTime 格式的时间
     *
     * @param date Date 类型的时间
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 时间戳转 LocalDateTime 格式
     *
     * @param unixTime 时间戳格式
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(long unixTime) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTime), ZoneId.systemDefault());
    }

    /**
     * 此时是否在区间内 默认开开
     * 方法内当前时间取到秒级(无毫秒)
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return true 在
     */
    public static boolean isInDateTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return isInDateTimeRange(startTime, endTime, 0);
    }

    /**
     * 此时是否在区间内 默认开开
     * 方法内当前时间取到秒级(无毫秒)
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return true 在
     */
    public static boolean isInDateTimeRange(LocalDateTime checkDateTime, LocalDateTime startTime, LocalDateTime endTime) {
        return isInDateTimeRange(checkDateTime, startTime, endTime, 0);
    }

    /**
     * 此时此刻是否在时间区间
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param interval  0 开开 1 开闭 2 闭开 3 闭闭
     * @return true 在
     */
    public static boolean isInDateTimeRange(LocalDateTime startTime, LocalDateTime endTime, int interval) {
        return isInDateTimeRange(LocalDateTime.now().withNano(0), startTime, endTime, interval);
    }

    /**
     * 此时此刻是否在时间区间
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param interval  0 开开 1 开闭 2 闭开 3 闭闭
     * @return true 在
     */
    public static boolean isInDateTimeRange(LocalDateTime checkDateTime, LocalDateTime startTime, LocalDateTime endTime, int interval) {
        YsaParamAssert.isTrue(interval >> 2 == 0, "判断区间参数输入错误");
        return ((interval >> 1) == 1 ? (!checkDateTime.isBefore(startTime)) : (checkDateTime.isAfter(startTime))) && ((interval & 1) == 1 ? (!checkDateTime.isAfter(endTime)) : (checkDateTime.isBefore(endTime)));
    }
}
