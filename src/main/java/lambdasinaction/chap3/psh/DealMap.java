package lambdasinaction.chap3.psh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 仿写map 接口
 */
public class DealMap {
    public static void main(String[] args) {

        //传递一个无参的构造函数着行为 返回这个 构造方法的引用
        Supplier<Apple> supplier = Apple::new;
        //System.out.println(supplier.get());


        //传递一个有参的构造函数  这个函数接受一个 110 返回一个 Apple
        Function<Integer,Apple> c2 = Apple::new;
        Apple apply = c2.apply(110);
        System.out.println(apply);

        /************************根据上面的例子，我们可以模拟 λ的map方法，然后再泛型化***************************/

        List<Integer> weights = Arrays.asList(7,3,4,10);
        List<Apple> map = map(weights, Apple::new);
        System.out.println(map);

    }

    /************************仿写 λ处理集合的map **********************************/
    public static List<Apple> map(List<Integer> weights,Function<Integer,Apple> f){
        if(weights==null){
            return null;
        }
        List<Apple> result = new ArrayList();
        for (Integer weight:weights) {
            Apple apple = f.apply(weight);
            result.add(apple);
        }
        return result;
    }

    /**********************加深 λ处理集合的map **********************************/

   /* public static <R> List<R> map2(List<T> o,Function<T,R> f){
        if(o==null){
            return null;
        }
        List<R> result = new ArrayList();
        for (Integer weight:weights) {
            Apple apple = f.apply(weight);
            result.add(apple);
        }
        return result;
    }*/


}
