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
     * @param idetify
     * @param <S>
     * @param <C>
     */
    public static  <S,C> void copyIfNotNull (Supplier<S>supplier,Consumer<C> consumer,Function<S,C> idetify) {
        S value = supplier.get();
        if (value != null) {
            consumer.accept(idetify.apply(value));
        }
    }


    public static void main(String[] args) {
        Person psh = new Person("psh","nan",18);
        Person psh2 = new Person();
        Person psh3 = new Person();

        CopyUtilFunction.copyIfNotNull(psh::getName,psh2::setName);
        CopyUtilFunction.copyIfNotNull(psh::getName,psh3::setName,String::toUpperCase);

        System.out.println(psh);
    }
}

