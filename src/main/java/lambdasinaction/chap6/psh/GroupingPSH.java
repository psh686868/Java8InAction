
package lambdasinaction.chap6.psh;
import lambdasinaction.chap6.Dish;

import java.util.*;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.menu;


public class GroupingPSH {

    static String DIET = "DIET";
    static String NORMAL = "NORMAL";
    static String FAT = "FAT";

    enum CaloricLevel {DIET, NORMAL, FAT};

    public static void main(String ... args) {
        /**根据类型进行分组*/
        System.out.println("Dishes grouped by type: " + groupDishesByType());

        /** 你可能想把热量不到400卡路里的菜划分为“低热量”（diet），
         * 热量400到700卡路里的菜划为“普通”（normal），
         * 高于700卡路里的划为“高热量”（fat）
         *
         * 个人心得： 其实分组返回的是一个map 它的键类型是 return返回类型 键值就是return的所有不同值
         *           值就是Stream里的分组后的数据
         *
         * */

        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());

        /** 两次分组 可以理解城一个 n维 分类表 详见web 页数 432页 */
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());

        /**
         * 我们看到可以把第二个group-ingBy收集器传递给外层收集器来实现多级分组。
         * 但进一步说，传递给第一个groupingBy的第二个收集器可以是任何类型，
         * 而不一定是另一个group-ingBy。例如，要数一数菜单中每类菜有多少个，
         * 可以传递counting收集器作为groupingBy收集器的第二个参数：
         * 查看源码可知 第二个参数是Collertors
         * 计算分组后的countDishesInGroups
         * */

        System.out.println("Count dishes in groups: " + countDishesInGroups());

        /***
         * 查找分组后每个热量的最大值
         *
         */


    }

    private static Map<Dish.Type,Long> countDishesInGroups() {
        System.out.println("################countDishesInGroups");
        Map<Dish.Type, Long> collect = menu.stream().collect(groupingBy(Dish::getType, counting()));
        return collect;
    }


    private static Map<String,List<Dish>> groupDishesByCaloricLevel() {
        System.out.println("###################groupDishesByCaloricLevelÎ");
        Map<String, List<Dish>> collect = menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return GroupingPSH.DIET;
            } else if (dish.getCalories() <= 700) {
                return GroupingPSH.FAT;
            } else return GroupingPSH.NORMAL;

        }));
        return collect;
    }
    /**
     * 还要注意，普通的单参数groupingBy(f)
     * （其中f是分类函数）实际上是groupingBy(f,toList())的简便写法。
     *
     * web p 433页
     * */
    private static  Map<Dish.Type, List<Dish>> groupDishesByType() {
        return menu.stream().collect(groupingBy(Dish::getType));
    }

    private static Map<Dish.Type,Map<String,List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        System.out.println("##############groupDishedByTypeAndCaloricLevel");
        Map<Dish.Type, Map<String, List<Dish>>> collect = menu.stream().collect(groupingBy(Dish::getType, groupingBy(
                (Dish dish) -> {
                    if (dish.getCalories() <= 400) return GroupingPSH.FAT;
                    else if (dish.getCalories() <= 700) return GroupingPSH.DIET;
                    else return GroupingPSH.NORMAL;
                }
        )));
        return collect;
    }



}

