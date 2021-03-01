package cn.com.quanyou.ioc.common.component.util;

import cn.com.quanyou.ioc.common.component.exception.ArgumentNotValidException;
import cn.com.quanyou.ioc.common.component.util.utilBean.TimeArgs;
import com.alibaba.fastjson.JSON;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @Description //日期时间工具类
 * @Author yangli
 * @Date 2019/9/16-14:44
 **/
public class DateTimeUtils {

    public static final String DATEFORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * @Description: 获取年
     * @Return: java.lang.String
     * @Author: yangli
     * @Date: 2019/9/16-14:47
     **/
    public static String getYear() {
        return String.valueOf(LocalDateTime.now().getYear());
    }

    /**
     * @Description: 获取月
     * @Return: java.lang.String
     * @Author: yangli
     * @Date: 2019/9/16-14:47
     **/
    public static String getMonth() {
        return String.valueOf(LocalDateTime.now().getMonthValue());
    }

    /**
     * @Description: 获取日
     * @Return: java.lang.String
     * @Author: yangli
     * @Date: 2019/9/16-14:47
     **/
    public static String getDay() {
        return String.valueOf(LocalDateTime.now().getDayOfMonth());
    }

    /**
     * @param dateTimeStr: eg:201909
     * @Description: 获取同比请求接口的时间参数
     * @Return: cn.com.quanyou.ioc.marketdataquery.common.TimeArgs
     * @Author: yangli
     * @Date: 2019/9/16-17:08
     **/
    public static TimeArgs getCurrent2CompareTimes(String dateTimeStr) {
        TimeArgs result = new TimeArgs();
        try {
            LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(dateTimeStr.substring(0, 4)), Integer.parseInt(dateTimeStr.substring(4, 6)), 1, 0, 0);
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime currentStartTime = dateTime.with(TemporalAdjusters.firstDayOfMonth());
            LocalDateTime currentEndTime = currentTime;
            if (currentTime.getMonthValue() == dateTime.getMonthValue()) {
                // 同月
                if (currentTime.getDayOfMonth() != 1) {
                    // 日期取当前日的前一天
                    currentEndTime = currentTime.plusDays(-1);
                }
            } else {
                // 取该月的最后一天
                currentEndTime = dateTime.with(TemporalAdjusters.lastDayOfMonth());
            }

            result.setCurrentStart(currentStartTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            result.setCurrentEnd(currentEndTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

            // 同比时间设置
            LocalDateTime compareStartTime = currentStartTime.plusYears(-1);
            LocalDateTime compareEndTime = currentEndTime.plusYears(-1);
            result.setCompareStart(compareStartTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            result.setCompareEnd(compareEndTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

            return result;
        } catch (Exception e) {
            throw new ArgumentNotValidException("日期参数不正确");
        }
    }

    /**
     * @Description: 验证日期参数格式
     * @param dateTimeStr
     * @Return: void
     * @Author: yangli
     * @Date: 2019/9/19-15:35
     **/
    public static void validateDate(String dateTimeStr) {
        try {
            LocalDate localDate = LocalDate.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            throw new ArgumentNotValidException("日期格式错误");
        }
    }

    /**
     * @Description: 获取指定格式的今天时间
     * @Return: java.lang.String
     * @Author: yangli
     * @Date: 2019/9/19-10:47
     **/
    public static String getTodayByFormat(String dateFormat) {
        if (StringUtils.isBlank(dateFormat)) {
            return getTodayByFormat();
        }
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * @Description: 获取今天时间[格式yyyyMMdd]
     * @Return: java.lang.String
     * @Author: yangli
     * @Date: 2019/9/19-10:47
     **/
    public static String getTodayByFormat() {

        return getTodayByFormat(DATEFORMAT_YYYYMMDD);
    }

    /**
     * @Description: 获取当天以interval为间隔天数的指定格式日期
     * @param interval 时间间隔-天数
     * @param dateFormat 时间格式
     * @Return: 日期eg：20191005
     * @Author: yangli
     * @Date: 2019/10/5-15:50
     **/
    public static String getIntervalDayFromTodayByFormat(Integer interval, String dateFormat) {

        return LocalDateTime.now().plusDays(getIntervalNumber(interval)).format(DateTimeFormatter.ofPattern(dateFormat));
    }

    private static int getIntervalNumber(Integer interval) {
       if(null == interval){
           // 为空时默认当天
           interval = 0;
       } else {
           // 以后的天数减1
           if (interval > 0){
               interval--;
           }
           // 以前的天数加1
           if (interval < 0){
               interval++;
           }
       }

        return interval;
    }

    /**
     * @Description: 获取当天以interval为间隔天数的日期【格式为yyyyMMdd】
     * @param interval 时间间隔-天数【负数表示以前，正数表示以后】
     * @Return: 日期eg：20191005
     * @Author: yangli
     * @Date: 2019/10/5-15:52
     **/
    public static String getIntervalDayFromToday(Integer interval) {

        return LocalDateTime.now().plusDays(getIntervalNumber(interval)).format(DateTimeFormatter.ofPattern(DATEFORMAT_YYYYMMDD));
    }

    public static void main(String[] args) {
        System.err.println(JSON.toJSONString(DateTimeUtils.getCurrent2CompareTimes("200002")));

        DateTimeUtils.validateDate("20190919");

        System.out.println(getIntervalDayFromToday(0));
        System.out.println(getIntervalDayFromTodayByFormat(-3, "yyyyMMdd hh:mm:ss"));
    }



}
