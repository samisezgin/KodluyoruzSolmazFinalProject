package com.samisezgin.finalproject.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public final class CustomDateTimeConverter {

    private CustomDateTimeConverter() {
    }

    public static LocalDateTime convert(String strDateTime) {
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .optionalStart()
                .appendPattern(" HH:mm")
                .optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
        return LocalDateTime.parse(strDateTime, format);
    }
}
