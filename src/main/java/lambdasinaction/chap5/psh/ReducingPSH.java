package lambdasinaction.chap5.psh;

import lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author shitou
 * @date
 * ，因为你可以将这个操作看成把一张长长的纸（你的流）反复折叠成一个小方块，而这就是折叠操作的结果。
 */
public class ReducingPSH {
    public static void main(String[] args) {
        //如对一个数组进行求值
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);

        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum+=numbers.get(i);
        }
        System.out.println(sum);

        System.out.println("###################");
        /** 分析
         * numbers中的每个元素都用加法运算符反复迭代来得到结果。
         * 通过反复使用加法，你把一个数字列表归约成了一个数字。
         * 这段代码中有两个参数：
         *   •总和变量的初始值，在这里是0；
         *   •将列表中所有元素结合在一起的操作，在这里是+
         * 所以我们可以进行对它进行抽象 ，一个初始值 另一个是运算符
         *
         * p333
         *   */
        Integer sum1 = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum1);

        System.out.println("###################");

        Integer sum2 = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(sum2);

        System.out.println("###################");

        Optional<Integer> sum3 = numbers.stream()
                .reduce(Integer::sum);
        System.out.println(sum3.get());

        List<Integer> numbers2 = Arrays.asList(3);

        Optional<Integer> sum4 = numbers2.stream()
                .reduce(Integer::sum);
        System.out.println(sum4.get());
        System.out.println("###################4");

        numbers.stream().reduce(Integer::max)
                .ifPresent(System.out::println);

        numbers.stream().reduce((a,b)->a>b?a:b)
                .ifPresent(System.out::println);

        /**
         * 怎么使用map和reduce 的方法数一数流中有多少个菜呢
         * map和reduce的连接通常称为map-reduce模式，因Google用它来进行网络搜索而出名，因为它很容易并行化。
         *
         */
        System.out.println("###################");
        List<Dish> dishes = Dish.menu;
        Integer count = dishes.stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);
        long count2 = dishes.stream().count();

        System.out.println(count);
        System.out.println(count2);

        List<CarDetailVideo> carDetailVideos = CarDetailVideo.carDetailVideos;
        System.out.println("###################");
        carDetailVideos.stream()
                .sorted(Comparator.comparing(carDetailVideo -> carDetailVideo.getSort()==null?1:carDetailVideo.getSort()))
                .forEach(System.out::println);
    }
}
