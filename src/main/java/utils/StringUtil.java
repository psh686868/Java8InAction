package utils;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    public static void notBlankAction(String str, Runnable action) {
        if (StringUtils.isNotBlank(str)) {
            action.run();
        }
    }

    public static <T>void noNullAccept(T value, Consumer<T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    public static <R>void notBankAccept(String str, Consumer<R> consumer, Function<String, R> function) {
        if (StringUtils.isNotBlank(str)) {
            R r = function.apply(str);
            consumer.accept(r);
        }
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.test2();
        String count = "";
        Person person = new Person();
        StringUtil.notBankAccept(count,person::setAge, Integer::parseInt);
        //System.out.println("person.getAge() = " + person.getAge());
        int length = count.split(",").length;

        String aa = "aa,bb";
        List<String> collect = Stream.of(aa.split(",")).map(StringUtil::getName)
            .filter(StringUtils::isNotBlank).collect(Collectors.toList());
        System.out.println("collect = " + collect);


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
