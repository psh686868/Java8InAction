package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import threads.Foo;
import utils.testhelp.Person;

/**
 * @author PSH
 * Date: 2018/5/29
 */
public class StringUtil {

    public static void notBankAccept(String str, Consumer<String> consumer) {
        if (StringUtils.isNotBlank(str)) {
            consumer.accept(str);
        }
    }

    public static <R> void notBankAccept(String str, Consumer<R> consumer, Function<String, R> function) {
        if (StringUtils.isNotBlank(str)) {
            R r = function.apply(str);
            consumer.accept(r);
        }
    }

    public static <T> void noNullAccept(T value, Consumer<T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    public static void notBlankAction(String str, Runnable action) {
        if (StringUtils.isNotBlank(str)) {
            action.run();
        }
    }

    public static String noBlankElseS2(Supplier<String> s1, Supplier<String> s2) {
        return noNullBlackElseGet(s1, s2, true);
    }

    public static <T> T noNullElseS2(@NonNull Supplier<T> s1, @NonNull Supplier<T> s2) {
        return noNullBlackElseGet(s1, s2, false);
    }

    public static <T> T noNullBlackElseGet(@NonNull Supplier<T> s1, @NonNull Supplier<T> s2, Boolean s1NoBank) {
        T t = s1.get();
        if (s1NoBank && t != null && (t instanceof String)) {
            if (StringUtils.isBlank((String) t)) {
                return s2.get();
            }
        }
        if (t != null) {
            return t;
        }
        return s2.get();
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.test2();
        String count = "";
        Person person = new Person();
        person.setAge(12);
        person.setName("");

        Person person2 = new Person();
        person2.setName("你好");
        person2.setAge(13);


        StringUtil.notBankAccept(count,person::setAge, Integer::parseInt);
        //System.out.println("person.getAge() = " + person.getAge());
        int length = count.split(",").length;

        String aa = "aa,bb";
        List<String> collect = Stream.of(aa.split(",")).map(StringUtil::getName)
            .filter(StringUtils::isNotBlank).collect(Collectors.toList());
       // System.out.println("collect = " + collect);
        Map map = new HashMap();
        map.put("11", "ds");


    }
    static Optional<String> getName () {
        return Optional.ofNullable("12");
    }

    static String getName (String aa) {
        if ("aa".equals(aa)) {
            return aa;
        }else {
            return null;
        }
    }


}
