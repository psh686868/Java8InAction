package lambdasinaction.chap5.psh;

import lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreamsPSH {

    public static void main(String...args){
    
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        List<Dish> menu = Dish.menu;

        //使用intStream   对menu中的卡路里求和
        Integer sun1 = menu.stream()
                .map(dish -> dish.getCalories())
                .reduce(0, Integer::sum);
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sum==sun1);

        /**将intStream转化为对象流*/
        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();

        /**因为intStream的默认值是0 所以如果要 求最大值 需要另求方法 max.orElse(1) 如果没有最大值返回一个1 */
        OptionalInt maxOption = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max;
        if (maxOption.isPresent()){
            max = maxOption.getAsInt();
        }else {
            max = 1;
        }

        /** IntStream的一些常用的操作*/
        IntStream numbersCount = IntStream.rangeClosed(0, 100)
                .filter(num -> num % 2 == 0);
        System.out.println(numbersCount.count());

        /** 勾股数*/

    }
   
    public static boolean isPerfectSquare(int n){
        return Math.sqrt(n) % 1 == 0;
    }

}
