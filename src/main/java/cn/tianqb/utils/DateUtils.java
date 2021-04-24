package cn.tianqb.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.StringJoiner;

@Slf4j
public class DateUtils {

    public static LocalDateTime date2LocalDateTime() {
        Date todayDate = new Date();
        return todayDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Date localDateTime2Date() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String localDate2String(String format, LocalDate date) {
        if (ObjectUtils.isEmpty(format)) {
            format = "yyyy-dd-MM";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return date.format(dateTimeFormatter);
    }

    public static Date dateBuilder(LocalDate localDate, String timeStr) {
        String localDateTime = localDate2String(null, localDate);
        StringJoiner time = new StringJoiner(" ");
        String dateTime = time.add(localDateTime.split(" ")[0]).add(timeStr).toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dateTime);
        } catch (ParseException e) {
            log.error("时间转换发生异常", e);
        }
        return date;
    }
}
