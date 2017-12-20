package reactive.function.chap1;

import java.util.List;
import java.util.function.Function;

/**
 * Create by psh
 * Date: 2017/11/26
 */
public class DemoFunction_composition_Basic {
    public static void main(String[] args) {
        Function<Integer, Integer> add = number ->number+2;
        Function<Integer, Integer>square =number ->
                number*number;
        Integer value_add=add.apply(5);
        Integer value_square=square.apply(5);
        System.out.println("addition:- "+value_add +
                "\tsquare:- "+value_square);

        /**
         * 打印先加2后再5的平方
         */

        /**
         * 117 到118 比较不错，简单的介绍了 函数式的由来
         * 我们能不能有种方法进行对函数进行增强 ， 如 先计算 + 2 后在进行平方 所以函数式体统了 andThen方法，当第一个函数的计算
         * 结果返回给第二个函数用
         */
        System.out.println(square.apply(value_add));
        Integer apply = add.andThen(square).apply(5); // 函数先加 之后执行 
        System.out.println(apply);//49

        Integer apply1 = add.compose(square).apply(5); // 调用链 函数式从 里到外执行
        System.out.println(apply1);//27

    }

    public static int matchCount_new(List<String> names_input, String name) {
        long count=names_input.stream().
                filter(n->n.equals(name)).count();
        return (int)count;
    }
}
