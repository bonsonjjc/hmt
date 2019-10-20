package com.zs.comm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static String format(Date date, String formatter) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter, Locale.CHINA);
        try {
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String format(String dateStr, String formatter, String toPattern, Date defValue) {
        Date date = parse(dateStr, formatter, defValue);
        return format(date, toPattern);
    }

    public static Date parse(String date, String pattern, Date fail) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return fail;
        }
    }

    public static Map<String, Double> fillDate(String start, String end) {

        Map<String, Double> dateMap = new LinkedHashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startTime = dateFormat.parse(start);
            Date endTime = dateFormat.parse(end);
            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(startTime);
            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(endTime);
            dateMap.put(dateFormat.format(startTime), 0.0);
            // 测试此日期是否在指定日期之后
            while (endTime.after(calBegin.getTime())) {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                dateMap.put(dateFormat.format(calBegin.getTime()), 0.0);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateMap;
    }
}
