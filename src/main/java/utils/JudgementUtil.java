package utils;


import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author PSH
 * Date: 2018/4/25
 * 主要是处理一些if (StringUtils.isBlank) else {} 这样的语句
 */
public class JudgementUtil<T> {

    private final T value;

    public JudgementUtil(T val) {
        value = val;
    }

    public static <T> JudgementUtil newIns(T value) {
        return new JudgementUtil(value);
    }

    public static <T extends String> JudgementUtil newIns(T value) {
        return new JudgementUtil(value);
    }

    public JudgementUtil whenTrueAndDo (Predicate<T> predicate, Runnable runAction) {
        Objects.requireNonNull(predicate, "predicate 不能为空");
        if (predicate.test(value)) {
            runAction.run();
        }
        return this;
    }

    /**
     * consume 函数接收的值可能为 null
     * 可以使用predicate进行排除，但predicate只能排除一个值
     * @param predicate p
     * @param consumer c
     */
    public JudgementUtil whenTrueAndDo (Predicate<T> predicate, Consumer<? super T> consumer) {
        Objects.requireNonNull(predicate, "predicate 不能为空");
        if (predicate.test(value)) {
            consumer.accept(value);
        }
        return this;
    }

    public JudgementUtil vlueNoNullDo ( Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer, "consumer 不能为空");
        if (checkValue()) {
            consumer.accept(value);
        }
        return this;
    }

    public JudgementUtil elseDo ( Runnable runAction) {
        Objects.requireNonNull(runAction, "runAction 不能为空");
        runAction.run();
        return this;
    }

    public JudgementUtil elseVlauNullDo (Runnable runAction) {
        if (checkValue()) {
            return this;
        } else {
            runAction.run();
        }
        return this;
    }

    private boolean checkValue() {
        return value != null;
    }


    public static void main(String[] args) {
        JudgementUtil.newIns("nihao").vlueNoNullDo(vlue -> {
            System.out.println(vlue);
        });

    }



}
