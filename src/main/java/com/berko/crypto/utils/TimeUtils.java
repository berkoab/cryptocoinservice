package com.berko.crypto.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Date timeToDate(long time) {
        ZonedDateTime historicalDate = ZonedDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault())
                .truncatedTo(ChronoUnit.DAYS);
        return Date.from(historicalDate.toInstant());
    }

    public static String timeToString(long time) {
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault())
                .truncatedTo(ChronoUnit.DAYS)
                .format(formatter);
    }

    public static long stringToLong(String input) {
        try {
            Date date = sdf.parse(input);
            return TimeUnit.MILLISECONDS.toSeconds(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Date stringToDate(String input) {
        try {
            Date date = sdf.parse(input);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
