package utils;



import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * PSHPropertyMapper.from()
 *                  .get
 * @param <T>
 */

public final class PSHPropertyMapper<T> {


    final T value ;
    private Boolean noNullflag = true ;

    private PSHPropertyMapper (T t) {
        if (t == null) {
            noNullflag = false;
        }
        value = t;
    }


    public static <T> PSHPropertyMapper<T>  from (Supplier<T> supplier) {
        T t = supplier.get();
        return new PSHPropertyMapper<>(t);
    }

    /**
     *
     * @param supplier 获取数据
     * @param nullAction 当时null时执行
     * @param <T>
     * @return
     */
    public static <T> void fromNullOrElse(Supplier<T> supplier,Runnable nullAction) {
        T t = supplier.get();
        if (t == null) {
            nullAction.run();
        }
    }

    /**
     *
     * @param supplier
     * @param supplierOther
     * @param <T>
     * @return
     */
    public static <T> PSHPropertyMapper<T>  fromNullOrGet(Supplier<T> supplier,Supplier<T> supplierOther) {
        T t = supplier.get();
        if (t == null) {
            t = supplierOther.get();
        }
        return new PSHPropertyMapper<>(t);
    }




}
