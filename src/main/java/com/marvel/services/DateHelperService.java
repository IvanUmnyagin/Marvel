package com.marvel.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public interface DateHelperService {

    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    int DATE_FORMAT_LENGTH = DATE_FORMAT.length();

    default LocalDateTime parseStringDateFormatToLocalDateTime(String dateTime) throws DateTimeParseException {

        return LocalDateTime
                .parse(dateTime, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    default String parseLocalDateTimeFormatToString(LocalDateTime dateTime){

        return dateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT));

    }

}
