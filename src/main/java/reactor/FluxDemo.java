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
       Flux.just("foo", "bar" ,"foobar").subscribe(System.out::println);
       Flux.fromArray(new Integer[]{1,2,3}).subscribe(System.out::println);
       Flux.empty().subscribe(System.out::println);
       Flux.range(1,10).subscribe(System.out::println);
       //Flux.generate(synchronousSink -> )
    }

}
