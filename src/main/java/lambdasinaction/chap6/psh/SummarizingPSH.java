package lambdasinaction.chap6.psh;

import lambdasinaction.chap6.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.menu;

/***
 * 这一章将对收集器进行练习，它在 java.util.stream里的 Collectors的方法
 */
public class SummarizingPSH {

    public static void main(String ... args) {

        // 使用收集器来给流中的元素 计数 ， 求出有多少个菜单

        System.out.println("Nr. of dishes: "+howManyDishes());

        // 查找流中的最大值和最小值 如：查找菜单中热量最高的菜
        System.out.println("The most caloric dish is: " + findMostCaloricDish());

        //进行对上面的方法进行封装
        System.out.println("findMostCaloricDishUsingComparator：The most caloric dish is: " + findMostCaloricDishUsingComparator());

        // 获取总热量
        System.out.println("Total calories in menu: " + calculateTotalCalories());

        //获取总热量的平均值
        System.out.println("Average calories in menu: " + calculateAverageCalories());

        /**如果既能获得平均值，又要返回总和，还要获得最大值和最小值 呢 ，这个非常常用，详看web 409页*/
        System.out.println("Menu statistics: " + calculateMenuStatistics());

        /**##############练习join 收集器方法*/
        System.out.println("Short menu: " + getShortMenu());

        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
    }


    private static long howManyDishes() {
        long count = menu.stream().count();//这个是我添加的和下面进行比较
        System.out.println(count);
        return menu.stream().collect(counting());
    }

    private static Dish findMostCaloricDish() {
        System.out.println("#######findMostCaloricDish 方法  ######");
        Optional<Dish> reduce = menu.stream()
                .reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        System.out.println(reduce.get());

        Optional<Dish> collect = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        return collect.get();
    }

    private static Dish findMostCaloricDishUsingComparator() {
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        BinaryOperator<Dish> dishBinaryOperator = BinaryOperator.maxBy(dishComparator);
        Optional<Dish> collect = menu.stream().collect(reducing(dishBinaryOperator));
        return collect.get();
    }

    private static int calculateTotalCalories() {
        System.out.println("###############calculateTotalCalories############");
        Integer reduce = menu.stream()
                .map(dish -> dish.getCalories())
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);//这是我添加的
        return menu.stream().collect(summingInt(Dish::getCalories));
    }

    private static Double calculateAverageCalories() {
        System.out.println("##########calculateAverageCalories###########");
       return menu.stream().collect(averagingInt(Dish::getCalories));
    }

    private static IntSummaryStatistics calculateMenuStatistics() {
        System.out.println("###################IntSummaryStatistics###########");
        IntSummaryStatistics collect = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(collect.getMax());
        System.out.println(collect.getAverage());
        System.out.println(collect.getSum());
        return collect;
    }

    private static String getShortMenu() {
        Optional<String> reduce = menu.stream().map(dish -> dish.getName())
                .reduce((n1, n2) -> n1 + n2);
        System.out.println("############getShortMenu");
        System.out.println(reduce.get());
        return menu.stream().map(Dish::getName).collect(joining());
    }

    private static String getShortMenuCommaSeparated() {
        return menu.stream().map(Dish::getName).collect(joining(","));
    }
}
