package utils.testhelp;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author PSH
 * Date: 2017/12/29
 * 流水线版的处理数据
 *
 */
public class DataPropertyMapper {

    private final static DataPropertyMapper instance = new DataPropertyMapper();
    private DataPropertyMapper () {

    }

    /**
     *
     * @param supplier 数据出处函数
     * @param consumer 目标数据获取函数
     * @param <T>
     */
    public static  <T> void copyIfNotNull (Supplier<T> supplier, Consumer<T> consumer) {
        copyIfNotNull (supplier,consumer, Function.identity());
    }

    /**
     *
     * @param supplier 数据出处
     * @param consumer 目标数据获取函数
     * @param function 转换函数，类似于Stream的Map
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
