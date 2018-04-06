package reactor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import utils.testhelp.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author PSH
 * Date: 2017/12/18
 */
public class FluxDemo {

    public static void main(String[] args) {
//        Flux<String> datas = Flux.just("foo", "bar" ,"foobar");
//        Flux<String> datasAway = Flux.fromIterable( Arrays.asList("foo", "bar", "foobar"));
//        Greeter greeter = new Greeter ();

        //or
        JSONObject jsonObject = JSON.parseObject(null);
        if (jsonObject != null) {
            String pictureBig = (String)jsonObject.get("picture_big");
            System.out.println(pictureBig);
        }


    }

    static class Greeter implements Function<String, String> {
        public String apply(String name) {
            return "Hello " + name;
        }
    }

    private static Person dataConvent(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        Person car = new Person();
        Person car1 = new Person();

        //car.setAge(date2TimeSamp(ofNull(map.get("firstLicensePlateDate"))));
        car.setAge(car1.getAge());

        return car;
    }

    private static String ofNull(Object obj) {
        if (obj != null) {
            return obj.toString();
        } else {
            return "";
        }

    }

    private static Integer date2TimeSamp(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse;
        try {
            parse = simpleDateFormat.parse(date);
            Long unixTimeStame = parse.getTime() / 1000;
            return unixTimeStame.intValue();
        } catch (ParseException e) {
            return null;
        }
    }

    private static String getMainPicture ( String pictures) {
        if (StringUtils.isBlank(pictures)) {
            return "";
        }
        Stream<String> pictureStream = Stream.of(pictures.split(","));
        Optional<String> firstPicture = pictureStream.findFirst();
        if (firstPicture.isPresent()) {
            return firstPicture.get();
        }
        return "";
    }

}
