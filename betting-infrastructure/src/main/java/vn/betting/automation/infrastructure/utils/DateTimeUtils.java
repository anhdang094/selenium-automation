package vn.betting.automation.infrastructure.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtils {

    public static TimeZone GMT7TimeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String YYYYMMDDHHMMSSSSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YYYYMMDD = "yyyyMMdd";

    public static String getLastDayOfMonth(String month, String inputformat, String outputFormat) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(convertDate(month, inputformat));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        DateFormat dateFormat = new SimpleDateFormat(outputFormat);
        return dateFormat.format(c.getTime());
    }

    public static String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        return dateFormat.format(new Date());
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String formatLocalDate(LocalDate localDate, String format) {
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }

    public static Date getCurDateWithMilisec() throws ParseException {
        SimpleDateFormat formatSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatSDF.setTimeZone(GMT7TimeZone);

        SimpleDateFormat parseSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date(System.currentTimeMillis());
        Date result = parseSDF.parse(formatSDF.format(date));

        return result;
    }

    public static Date convertDate(String dateStr, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
            if (format != null) {
                dateFormat = new SimpleDateFormat(format);
            }
            return dateFormat.parse(dateStr);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static String addTodayToTimeFormat(String timeStr) {
        String date = convertTimeStamp2Date(System.currentTimeMillis(), "dd/MM/yyyy");
        return date + " " + timeStr;
    }

    public static LocalDate convertToLocalDate(String dateTime, String format) {
        try {
            String pattern = StringUtils.isEmpty(format) ? "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" : format;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            if (!StringUtils.isEmpty(dateTime)) {
                return LocalDate.parse(dateTime, formatter);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime getFirstDateTimeOfMonth(String month, String format) {
        try {
            String pattern = StringUtils.isEmpty(format) ? "dd/MM/yyyy" : "dd-" + format;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            if (!StringUtils.isEmpty(month)) {
                return LocalDate.parse("01-" + month, formatter).atStartOfDay();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String second2DateStr(long timeStamp, String format) {
        DateFormat dateFormat;
        if (format == null) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        } else {
            dateFormat = new SimpleDateFormat(format);
        }
        return dateFormat.format(new Date(timeStamp * 1000));
    }

    public static String milisecond2DateStr(long timeStamp, String format) {
        DateFormat dateFormat;
        if (format == null) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        } else {
            dateFormat = new SimpleDateFormat(format);
        }
        return dateFormat.format(new Date(timeStamp));
    }

    public static long convertTimeStampMilisecond(String dateStr, String format) {
        try {
            DateFormat dateFormat;
            if (format == null) {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            } else {
                dateFormat = new SimpleDateFormat(format);
            }
            Date date = dateFormat.parse(dateStr);
            return date.getTime();
        } catch (Exception ex) {
            return 0;
        }
    }

    public static String getCurrentTime(String format) {
        SimpleDateFormat formatSDF = new SimpleDateFormat(format);
        Date date = new Date(System.currentTimeMillis());
        return formatSDF.format(date);
    }

    public static String getYYYYMMDDDayNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(new Date());
    }

    public static String getCurrentDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String convertString(Date date, String format) {
        DateFormat dateFormat;
        if (format == null) {
            dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        } else {
            dateFormat = new SimpleDateFormat(format);
        }
        return dateFormat.format(date);
    }

    public static String timeStamp2DateStr(long timestamp) {
        if (timestamp == 0) {
            return "";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return sdf.format(new Date(timestamp));
        } catch (Exception ex) {
            return "";
        }
    }

    public static String convertTimeStamp2Date(long timeStamp, String format) {
        DateFormat dateFormat;
        if (format == null) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        } else {
            dateFormat = new SimpleDateFormat(format);
        }
        return dateFormat.format(new Date(timeStamp));
    }

    public static Date add(Date date, int year, int month, int day, int hour, int minute,
                           int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DATE, day);
        cal.add(Calendar.HOUR, hour);
        cal.add(Calendar.MINUTE, minute);
        cal.add(Calendar.SECOND, second);

        return cal.getTime();
    }

    public static int getCurrentDayOfYear() {
        Calendar calendar = Calendar.getInstance();
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        return dayOfYear;
    }

    public static String fomartDate(SimpleDateFormat simpleDateFormat, long time) {
        if (time > 0) {
            return simpleDateFormat.format(new Date(time));
        }
        return "";
    }

    public static String getDate(int delDay, String format) {
        Calendar toDay = Calendar.getInstance();
        toDay.add(Calendar.DATE, delDay);
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(toDay.getTime());
    }

    public static String convertFormat(String dateStr, String oldFormat, String newFormat) {
        Date date = convertDate(dateStr, oldFormat);
        return convertString(date, newFormat);
    }

    public static String getDateByDateOfYear(String dateOfYear, String format) {
        DateFormat dateFormat;
        if (format == null) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        } else {
            dateFormat = new SimpleDateFormat(format);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, Integer.valueOf(dateOfYear));
        return dateFormat.format(calendar.getTime());
    }

    public static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static int getYmd() {

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("YYMMdd");
        format.setTimeZone(GMT7TimeZone);

        return NumberUtils.toInt(format.format(date));
    }

    public static int getYmd(Date date) {

        DateFormat format = new SimpleDateFormat("YYMMdd");
        format.setTimeZone(GMT7TimeZone);

        return NumberUtils.toInt(format.format(date));
    }
}
