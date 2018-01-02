package utils;


import utils.testhelp.Person;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author PSH
 * Create by psh
 * Date: 2017/12/23
 */
public class CopyUtilFunction {

    /**
     * 数据copy
     * @param supplier
     * @param consumer
     * @param <T>
     */
    public static  <T> void copyIfNotNull (Supplier<T>supplier,Consumer<T> consumer) {
        copyIfNotNull (supplier,consumer,Function.identity());
    }

    /**
     *
     * @param supplier
     * @param consumer
     * @param function
     * @param <S>
     * @param <C>
     */
    public static <S,C>  void copyIfNotNull (Supplier<S> supplier,Consumer<C> consumer,Function<S,C> function) {
        S value = supplier.get();
        if (value != null) {
            consumer.accept(function.apply(value));
        }
    }

}

