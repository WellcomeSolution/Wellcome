package com.example.wellcome.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {

    public static LocalDateTime parse(String date)
    {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
    }
}
