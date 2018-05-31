package utils;


import utils.testhelp.Person;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author PSH
 * Create by psh
 * Date: 2017/12/23
 */
public class CopyUtilFunction {

    /**
     * 数据copy
     */
    public static <T> void copyIfNotNull(Supplier<T> supplier, Consumer<T> consumer) {
        copyIfNotNull(supplier, consumer, Function.identity());
    }

    /**
     * @param supplier
     * @param consumer
     * @param function
     * @param <S>
     * @param <C>
     */
    public static <S, C> void copyIfNotNull(Supplier<S> supplier, Consumer<C> consumer, Function<S, C> function) {
        S value = supplier.get();
        if (value != null) {
            consumer.accept(function.apply(value));
        }
    }

    public static void main(String[] args) throws ParseException {
        String date = "2015-7-03 23:00:00";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(date);
//            System.out.println(parse.getTime());
            Long unixTimeStame = parse.getTime() / 1000;
//            System.out.println(unixTimeStame.intValue());
        } catch (ParseException e) {

        }
        Map<String, String> map = new HashMap<>();
        map.put("isNewCar", "1");
        //System.out.println( map.get("isNewCar") == null ||  Integer.valueOf(map.get("isNewCar").toString()).equals(0) ? false : true);
        // System.out.println( map.get("isNewCar") == null ? false : Integer.valueOf(map.get("isNewCar").toString()).equals(0) ? false:true);

        Arrays.asList(1, 5, 6, 4, 9, 7, 2, 3).stream().sorted((a, b) -> Integer.compare(b, a));
//        int i = Integer.parseInt("");
//        System.out.println(i);

    }

}


