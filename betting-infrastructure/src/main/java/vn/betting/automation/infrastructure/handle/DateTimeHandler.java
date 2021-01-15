package vn.betting.automation.infrastructure.handle;

import vn.betting.automation.infrastructure.enumeration.DateTimeType;

/**
 * @author anhdx
 */
public class DateTimeHandler {

    public static long convertToTimeSecond(int value, DateTimeType dateTimeType) {
        switch (dateTimeType) {
            case MILISECOND:
                return Long.valueOf(value / 1000);
            case SECOND:
                return Long.valueOf(value);
            case MINUTES:
                return Long.valueOf(value * 60);
            case HOUR:
                return Long.valueOf(value * 60 * 60);
            case DAY:
                return Long.valueOf(value * 24 * 60 * 60);
            case WEEK:
                return Long.valueOf(value * 7 * 24 * 60 * 60);
            case MONTH:
                return Long.valueOf(value * 30 * 24 * 60 * 60);
            case YEAR:
                return Long.valueOf(value * 365 * 24 * 60 * 60);
            default:
                return 0L;
        }

    }
}
