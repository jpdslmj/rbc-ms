package com.rbc.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import java.text.ParseException;

/**
 * 日期处理
 */
public class DateUtils {
    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

      /**
       * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
       *
       * @param strDate
       * @return
       */
        public static Date strToDateLong(String strDate) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date strtodate=null;
            try {
                strtodate = formatter.parse(strDate);
            }catch (ParseException e){
                e.printStackTrace();
            }
            return strtodate;
         }

       /**
        * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
        *
        * @param dateDate
        * @return
        */
         public static String dateToStrLong(java.util.Date dateDate) {
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             String dateString = formatter.format(dateDate);
             return dateString;
         }
      /**
       * 将短时间格式时间转换为字符串 yyyy-MM-dd
       *
       * @param dateDate
       * @param
       * @return
       */
       public static String dateToStr(java.util.Date dateDate) {
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
           String dateString = formatter.format(dateDate);
           return dateString;
       }

          /**
           * 将短时间格式字符串转换为时间 yyyy-MM-dd
           *
           * @param strDate
           * @return
           */
         public static Date strToDate(String strDate) {
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             Date strtodate=null;
            try{
                strtodate = formatter.parse(strDate);
            }catch (ParseException e){
                e.printStackTrace();
            }
            return strtodate;
         }
    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        } else if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }
}
