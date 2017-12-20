package lambdasinaction.chap6.psh;

import lambdasinaction.chap6.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.menu;

/***
 * 分区函数
 * 分区是分组的特殊情况：由一个谓词（返回一个布尔值的函数）作为分类函数，它称分区函数。
 * 分区函数返回一个布尔值，这意味着得到的分组Map的键类型是Boolean，
 * 于是它最多可以分为两组——true是一组，false是一组。
 */
public class PartitioningPSH {

    public static void main(String ... args) {
        //System.out.println("Dishes partitioned by vegetarian: " + partitionByVegeterian());
        //System.out.println("Vegetarian Dishes by type: " + vegetarianDishesByType());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());
    }

    /**
     *  进行对蔬菜进行分组
     *
     * @return
     */
    private static Map<Boolean, List<Dish>> partitionByVegeterian() {
        Map<Boolean, List<Dish>> collect = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        return collect;
    }

    /***
     * 找出所有是蔬菜的美食并且根据type进行分组
     * @return
     */
    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        System.out.println("##################vegetarianDishesByType");
        Map<Boolean, Map<Dish.Type, List<Dish>>> collect = menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        return collect;
    }

    /***
     * 找到素食和非素食中热量最高的菜
     * @return
     */
    private static Object mostCaloricPartitionedByVegetarian() {
        Map<Boolean, Dish> collect = menu.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        return collect;
    }
}

