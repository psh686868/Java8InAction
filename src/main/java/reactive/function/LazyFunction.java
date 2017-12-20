package reactive.function;

import java.util.function.Supplier;

/**
 * Create by psh
 * Date: 2017/11/23
 *
 * 使用函数的懒加载
 */
class LazyFunction {
    public static boolean evaluateMe(String data) {
        System.out.println("evaluation");
        return data.length() > 5;
    }

    public static String matchMe(boolean result1, boolean result2){
        return result1&&result2?" both values are equal" :
                "values are different";
    }

    public static String matchMeLazy (Supplier<Boolean> length1 , Supplier<Boolean> length2) {
        return (length1.get() && length2.get()) ? " both values are equal" :
                "values are different";
    }
}
