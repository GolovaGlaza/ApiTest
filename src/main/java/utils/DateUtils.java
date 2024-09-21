package utils;

import java.time.LocalDate;

public class DateUtils {

    public static String getCurrentDate(){
        return LocalDate.now().toString();
    }
}
