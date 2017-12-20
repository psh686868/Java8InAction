package lambdasinaction.chap1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilteringApplesPSH {


    public static void main(String ... args){

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                                              new Apple(155,"green"),
                                              new Apple(120,"red"));

        /*******************以前的方法进行进行苹果集合筛选 start******************/
//        inventory = FilteringApplesPSH.filterGreenApples(inventory);
//        inventory = FilteringApplesPSH.filterHeavyApples(inventory);
//        System.out.println(inventory);
        /*******************以前的方法进行进行苹果集合筛选 end******************/


        /*******************使用 Predicate 进行筛选 ，因为 Predicate是FunctionalInterface类型的，所以
         可以传入函数，去执行方法，详见使用 Predicate
         **********************************************************/
        //[Apple{color='green', weight=80}, Apple{color='green', weight=155}]
//        List<Apple> apples = FilteringApplesPSH.filterApples(inventory, FilteringApplesPSH::isGreenApple);
//        System.out.println(apples);
        /*******************使用 Predicate传入函数 进行筛选 end******************/


        /*******************使用 λ 函数 节约代码代替 函数 比如 isGreenApple 函数*/
        //[Apple{color='green', weight=80}, Apple{color='green', weight=155}, Apple{color='red', weight=120}]
        List<Apple> apples3 = FilteringApplesPSH.filterApples(inventory, (Apple apple)->"green".equals(apple.getColor()));
       // System.out.println(apples3);
        List<Apple> apples4 = FilteringApplesPSH.filterApples(inventory, (Apple apple)->apple.getWeight()>80&apple.getWeight()<150);
        //System.out.println(apples4);
        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 ||
                                                                       "red".equals(a.getColor()));
        //System.out.println(weirdApples);
        /**********************************end******************/


        /*******************使用 集合的stream 进行并行计算 ，然后使用 拦截filter进行数据处理 collect(Collectors.toList())将流转换为对象 */

        List<Apple> collect = inventory.stream().filter((Apple apple) -> apple.getWeight() >= 120).collect(Collectors.toList());
        System.out.println(collect);


        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
       /* List<Apple> greenApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApples2);*/
//
//        // [Apple{color='green', weight=155}]
//        List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
//        System.out.println(heavyApples2);
//
//        // []
//        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 ||
//                                                                       "brown".equals(a.getColor()));
//        System.out.println(weirdApples);
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static Boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> apples,Predicate<Apple> predicate){
        if(apples!=null){
            List<Apple> result = new ArrayList();
            for (Apple apple:apples) {
                if(predicate.test(apple)){
                    result.add(apple);
                }
            }
            return result;
        }
        return null;
    }


    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                   "color='" + color + '\'' +
                   ", weight=" + weight +
                   '}';
        }
    }

}
