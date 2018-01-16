package com.zl.third.brother.utils;

import android.text.TextPaint;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/**
 * 字符串工具类
 *
 * @author tangjun
 */
public class StringUtils {

    public static final String EMPTY = "";

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";
    /**
     * 用于生成文件
     */
    private static final String DEFAULT_FILE_PATTERN = "yyyy-MM-dd-HH-mm-ss";
    private static final double KB = 1024.0;
    private static final double MB = 1048576.0;
    private static final double GB = 1073741824.0;
    public static final SimpleDateFormat DATE_FORMAT_PART = new SimpleDateFormat(
            "HH:mm");

    public static String currentTimeString() {
        return DATE_FORMAT_PART.format(Calendar.getInstance().getTime());
    }

    public static char chatAt(String pinyin, int index) {
        if (pinyin != null && pinyin.length() > 0)
            return pinyin.charAt(index);
        return ' ';
    }

    /**
     * 获取字符串宽度
     */
    public static float GetTextWidth(String Sentence, float Size) {
        if (isEmpty(Sentence))
            return 0;
        TextPaint FontPaint = new TextPaint();
        FontPaint.setTextSize(Size);
        return FontPaint.measureText(Sentence.trim()) + (int) (Size * 0.1); // 留点余地
    }

    /**
     * string 转换为 int 异常捕获
     *
     * @param code
     * @return
     */
    public static int stringToInt(String code) {
        int num = 0;
        try {
            num = Integer.parseInt(code == null ? "0" : code);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return num;
    }

    /*
    * string 转为 int 并去掉开头的 0
    * */
    public static int stringToIntRemoveStartZero(String code) {
        String newStr = code.replaceFirst("^0*", "");
        return Integer.parseInt(newStr);
    }

    /**
     * string 转换为 double 异常捕获
     *
     * @param code
     * @return
     */
    public static double stringToDouble(String code) {
        double num = 0;
        try {
            num = Double.parseDouble(code == null ? "0" : code);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return num;
    }


    /**
     * string 转换为 Long 异常捕获
     *
     * @param code
     * @return
     */
    public static long stringToLong(String code) {
        long num = 0;
        try {
            num = Long.parseLong(code == null ? "0" : code);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * 格式化日期字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String formatDate(long date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(date));
    }

    /**
     * 格式化日期字符串
     *
     * @param date
     * @return 例如2011-3-24
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_PATTERN);
    }

    public static String formatDate(long date) {
        return formatDate(new Date(date), DEFAULT_DATE_PATTERN);
    }

    /**
     * 获取当前时间 格式为yyyy-MM-dd 例如2011-07-08
     *
     * @return
     */
    public static String getDate() {
        return formatDate(new Date(), DEFAULT_DATE_PATTERN);
    }

    /**
     * 生成一个文件名，不含后缀
     */
    public static String createFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FILE_PATTERN);
        return format.format(date);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getDateTime() {
        return formatDate(new Date(), DEFAULT_DATETIME_PATTERN);
    }

    /**
     * 格式化日期时间字符串
     *
     * @param date
     * @return 例如2011-11-30 16:06:54
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, DEFAULT_DATETIME_PATTERN);
    }

    public static String formatDateTime(long date) {
        return formatDate(new Date(date), DEFAULT_DATETIME_PATTERN);
    }

    /**
     * 格林威时间转换
     *
     * @param gmt
     * @return
     */
    public static String formatGMTDate(String gmt) {
        TimeZone timeZoneLondon = TimeZone.getTimeZone(gmt);
        return formatDate(Calendar.getInstance(timeZoneLondon)
                .getTimeInMillis());
    }

    /**
     * 拼接数组
     *
     * @param array
     * @param separator
     * @return
     */
    public static String join(final ArrayList<String> array,
                              final String separator) {
        StringBuffer result = new StringBuffer();
        if (array != null && array.size() > 0) {
            for (String str : array) {
                result.append(str);
                result.append(separator);
            }
            result.delete(result.length() - 1, result.length());
        }
        return result.toString();
    }

    public static String join(final Iterator<String> iter,
                              final String separator) {
        StringBuffer result = new StringBuffer();
        if (iter != null) {
            while (iter.hasNext()) {
                String key = iter.next();
                result.append(key);
                result.append(separator);
            }
            if (result.length() > 0)
                result.delete(result.length() - 1, result.length());
        }
        return result.toString();
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null");
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null ? EMPTY : str.trim();
    }

    /**
     * 转换时间显示
     *
     * @param time 毫秒
     * @return
     */
    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes,
                seconds) : String.format("%02d:%02d", minutes, seconds);
    }

    public static boolean isBlank(String s) {
        return StringUtils.isEmpty(s);
    }

    /**
     * 根据秒速获取时间格式
     */
    public static String gennerTime(int totalSeconds) {
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * 转换文件大小
     *
     * @param size
     * @return
     */
    public static String generateFileSize(long size) {
        String fileSize;
        if (size < KB)
            fileSize = size + "B";
        else if (size < MB)
            fileSize = String.format("%.1f", size / KB) + "KB";
        else if (size < GB)
            fileSize = String.format("%.1f", size / MB) + "MB";
        else
            fileSize = String.format("%.1f", size / GB) + "GB";

        return fileSize;
    }

    /**
     * 查找字符串，找到返回，没找到返回空
     */
    public static String findString(String search, String start, String end) {
        int start_len = start.length();
        int start_pos = StringUtils.isEmpty(start) ? 0 : search.indexOf(start);
        if (start_pos > -1) {
            int end_pos = StringUtils.isEmpty(end) ? -1 : search.indexOf(end,
                    start_pos + start_len);
            if (end_pos > -1)
                return search.substring(start_pos + start.length(), end_pos);
        }
        return "";
    }

    /**
     * 截取字符串
     *
     * @param search       待搜索的字符串
     * @param start        起始字符串 例如：<title>
     * @param end          结束字符串 例如：</title>
     * @param defaultValue
     * @return
     */
    public static String substring(String search, String start, String end,
                                   String defaultValue) {
        int start_len = start.length();
        int start_pos = StringUtils.isEmpty(start) ? 0 : search.indexOf(start);
        if (start_pos > -1) {
            int end_pos = StringUtils.isEmpty(end) ? -1 : search.indexOf(end,
                    start_pos + start_len);
            if (end_pos > -1)
                return search.substring(start_pos + start.length(), end_pos);
            else
                return search.substring(start_pos + start.length());
        }
        return defaultValue;
    }

    /**
     * 截取字符串
     *
     * @param search 待搜索的字符串
     * @param start  起始字符串 例如：<title>
     * @param end    结束字符串 例如：</title>
     * @return
     */
    public static String substring(String search, String start, String end) {
        return substring(search, start, end, "");
    }

    /**
     * 拼接字符串
     *
     * @param strs
     * @return
     */
    public static String concat(Object... strs) {
        StringBuffer result = new StringBuffer();
        if (strs != null) {
            for (Object str : strs) {
                if (str != null)
                    result.append(str);
            }
        }
        return result.toString();
    }

    /**
     * Helper function for making null strings safe for comparisons, etc.
     *
     * @return (s == null) ? "" : s;
     */
    public static String makeSafe(String s) {
        return (s == null) ? "" : s;
    }


    /**
     * 获得万为单位的数据
     *
     * @param src
     */
    public static String getCountByWan(String src) {
        try {
            int num = Integer.valueOf(src);
            if (num > 9999) {
                DecimalFormat df = new DecimalFormat("0.0");
                double d = (num * 1.0 / 10000);
                String db = df.format(d);
                return db + "万";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return src;
    }

    //    public static String getResourcePath(String path){
    //        if(path == null){
    //            return URL.BASE_RESOURCE;
    //        }
    //        if(!path.startsWith("http")){
    //            path = URL.BASE_RESOURCE + path;
    //        }
    //        return path;
    //    }

    /**
     * 增加0
     *
     * @param num
     * @return
     */
    public static String addZero(int num) {
        return (num < 10 ? "0" : "") + num;
    }

    public static String getWeightScope(int min, int max) {
        String string = null;
        if (min == 0 && max == 0) {
            string = StringUtils.concat("无限制");
        } else if (max == 0) {
            string = min + "kg以上";
        } else if (min == 0) {
            string = max + "kg以下";
        } else {
            string = StringUtils.concat(min, "～", max, "kg");
        }
        return string;
    }

    /**
     * 判断字符串内是否包含某个字符
     *
     * @param s
     * @return
     */
    public static int isHasChar(String s) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            list.add(String.valueOf(i));
        }
        list.add(list.size(), "a");
        int result = 0;

        breakFor:
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).equals(String.valueOf(s.charAt(i)))) {
                    result++;
                    break breakFor;
                }
            }
        }
        return result;
    }
}
