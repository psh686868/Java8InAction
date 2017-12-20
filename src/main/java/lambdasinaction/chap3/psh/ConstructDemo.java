package lambdasinaction.chap3.psh;

import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructDemo {
    public static void main(String[] args) {

        //传递一个无参的构造函数着行为 返回这个 构造方法的引用
        Supplier<Apple> supplier = Apple::new;
        //System.out.println(supplier.get());


        //传递一个有参的构造函数  这个函数接受一个 110 返回一个 Apple
        Function<Integer,Apple> c2 = Apple::new;
        Apple apply = c2.apply(110);
        System.out.println(apply);
    }
}
