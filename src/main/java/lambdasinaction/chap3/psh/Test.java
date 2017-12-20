package lambdasinaction.chap3.psh;


import java.util.function.Supplier;

public class Test {
    public static void main(String[] args) {
        Supplier<Apple> c1 = Apple::new;
        Apple apple1 = c1.get();
        System.out.println(apple1);
    }
}
