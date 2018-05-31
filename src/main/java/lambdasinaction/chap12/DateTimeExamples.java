package lambdasinaction.chap12;

import org.apache.commons.lang3.StringUtils;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeExamples {

    private static final ThreadLocal<DateFormat> formatters = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("dd-MMM-yyyy");
        }
    };

    public static void main(String[] args) {
//        useOldDate();
//        useLocalDate();
//        useTemporalAdjuster();
//        useDateFormatter();
//
//        LocalDate now = LocalDate.now();
//        LocalDate minus = now.minus(1, ChronoUnit.WEEKS);
//        //now.plus()
//        System.out.println("!!!!"+now);
//        System.out.println("!!!!"+minus);
//
//        System.out.println(now.toString());
//        System.out.println(minus.toString());

//        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
//        int l = 1516598308;
//        Long aLong = Long.valueOf(l) * 1000;
//        Long time = new Long((l * 1000));
//
//        String d = format.format(aLong);
//        System.out.println(d);
        String ss = "https://shiyong.souche.com/s/TeMjSyhcv7";

        String s = changUrl("http://www.baidu.com", ss);
        System.out.println(s);

    }

    private static void useOldDate() {
        Date date = new Date(114, 2, 18);
        System.out.println(date);

        System.out.println(formatters.get().format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.FEBRUARY, 18);
        System.out.println(calendar);
    }

    private static void useLocalDate() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear(); // 2014
        Month month = date.getMonth(); // MARCH
        int day = date.getDayOfMonth(); // 18
        DayOfWeek dow = date.getDayOfWeek(); // TUESDAY
        int len = date.lengthOfMonth(); // 31 (days in March)
        boolean leap = date.isLeapYear(); // false (not a leap year)
        System.out.println(date);

        int y = date.get(ChronoField.YEAR);
        int m = date.get(ChronoField.MONTH_OF_YEAR);
        int d = date.get(ChronoField.DAY_OF_MONTH);

        LocalTime time = LocalTime.of(13, 45, 20); // 13:45:20
        int hour = time.getHour(); // 13
        int minute = time.getMinute(); // 45
        int second = time.getSecond(); // 20
        System.out.println(time);

        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20); // 2014-03-18T13:45
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        System.out.println(dt1);

        LocalDate date1 = dt1.toLocalDate();
        System.out.println(date1);
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(time1);

        Instant instant = Instant.ofEpochSecond(44 * 365 * 86400);
        Instant now = Instant.now();

        Duration d1 = Duration.between(LocalTime.of(13, 45, 10), time);
        Duration d2 = Duration.between(instant, now);
        System.out.println(d1.getSeconds());
        System.out.println(d2.getSeconds());

        Duration threeMinutes = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println(threeMinutes);

        JapaneseDate japaneseDate = JapaneseDate.from(date);
        System.out.println(japaneseDate);
    }

    private static void useTemporalAdjuster() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date);
        date = date.with(lastDayOfMonth());
        System.out.println(date);

        date = date.with(new NextWorkingDay());
        System.out.println(date);
        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        date = date.with(new NextWorkingDay());
        System.out.println(date);

        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        date = date.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            }
            if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println(date);
    }

    private static class NextWorkingDay implements TemporalAdjuster {

        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            }
            if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }

    private static void useDateFormatter() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);

        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(date.format(formatter));
        System.out.println(date.format(italianFormatter));

        DateTimeFormatter complexFormatter = new DateTimeFormatterBuilder()
            .appendText(ChronoField.DAY_OF_MONTH)
            .appendLiteral(". ")
            .appendText(ChronoField.MONTH_OF_YEAR)
            .appendLiteral(" ")
            .appendText(ChronoField.YEAR)
            .parseCaseInsensitive()
            .toFormatter(Locale.ITALIAN);

        System.out.println(date.format(complexFormatter));
    }

    public static <T> Long getDate(T t) {
        if (t == null) {
            return null;
        }
        //判断是时间搓
        Pattern compile = Pattern.compile("[0-9]*");
        Matcher matcher = compile.matcher(t.toString());
        if (matcher.find()) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long time = new Long(matcher.group());
            String d = format.format(time);
            Date date = null;
            try {
                date = format.parse(d);
                return date.getTime();
            } catch (ParseException e) {
                throw new RuntimeException("时间转换错误: " + e);
            }
        } else {
            return 1L;
        }

    }

    public static <T> Integer getDate2(T t) {
        if (t == null) {
            return null;
        }
        Pattern compile = Pattern.compile("[0-9]*");
        Matcher matcher = compile.matcher(t.toString());
        if (matcher.find()) {
            String group = matcher.group();
            return Integer.parseInt(group);
        } else if (t instanceof String) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date = null;
            try {
                date = format.parse((String) t);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            System.out.print("Format To times:" + date.getTime());

            return null;
        }
        return null;


    }

    private static String changUrl(String siteDomain, String resultShareUrl) {
        if (StringUtils.isBlank(resultShareUrl) || StringUtils.isBlank(siteDomain)) {
            return "";
        }
        String linkId = "";
        String[] split = resultShareUrl.split("/sl/");
        List<String> strings = Arrays.asList(split);
        Optional<String> first = strings.stream().skip(1).findFirst();
        if (first.isPresent()) {
            linkId = first.get();
        }
        return siteDomain + "/venus/sl" + linkId;
    }
}
