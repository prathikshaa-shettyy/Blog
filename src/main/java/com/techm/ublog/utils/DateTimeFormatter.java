package com.techm.ublog.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * TODO: 7.32. Implement a method with the following signature.
 *  public static String format(LocalDateTime localDateTime)
 *  This method should convert the default date time to the human readable format[dd-MM-yyyy HH:mm:ss].
 */

public class DateTimeFormatter {
    public static String format(LocalDateTime localDateTime){
            LocalDate localDate = LocalDate.now();
            System.out.println(localDate);
            System.out.println(localDate.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            System.out.println("----------");
            LocalTime localTime = LocalTime.now();
            System.out.println(localTime);
            System.out.println(localTime.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
            //System.out.println(localTime.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a")));

        return null;
    }

}
