package utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author PSH
 * Create by psh
 * Date: 2017/12/23
 */
public class CopyUtilFunction {


    private static <T> void copyIfNotNull(Supplier<T> supplier, Consumer<T> consumer) {
        copyIfNotNull(supplier, consumer, Function.identity());
    }

    private static <S, C> void copyIfNotNull(Supplier<S> supplier, Consumer<C> consumer,
                                             Function<S, C> converter) {
        S value = supplier.get();
        if (value != null) {
            consumer.accept(converter.apply(value));
        }
    }

    public static Integer supplier () {
        return 1;
    }

    public static void consumer ( String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        Person psh = Person.builder().name("psh").age(18).sex("nam").build();
        System.out.println(psh);
        //CopyUtilFunction.copyIfNotNull(psh::getName);
    }
}
