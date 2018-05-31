package utils;

import java.util.Optional;
import java.util.function.Consumer;
import org.apache.commons.lang3.StringUtils;

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

    static Optional<String> getName () {
        return Optional.ofNullable("12");
    }

}
