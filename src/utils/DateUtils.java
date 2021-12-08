package utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author: cdw
 * @date: 2021/11/20 15:04
 * @description:
 */
public class DateUtils {

//    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getDate(java.util.Date date) {
        Date date1 = new Date(System.currentTimeMillis());
        return new Date(date.getTime());
    }
}
