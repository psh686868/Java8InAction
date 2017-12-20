package MasteringLambdasExcepress.chap2;

import java.util.function.UnaryOperator;

/**
 * Create by psh
 * Date: 2017/11/2
 */

public class Hello {
    Runnable runnable1 = () -> System.out.println(this + "!" + Thread.currentThread().getName());
    Runnable runnable2 = () -> System.out.println(toString() + "!" + Thread.currentThread().getName());
    UnaryOperator<String> b = x -> (x + 1).toString();

    /***
     * 内部类和兰布达表达式的 区别
     *  1. 内部类 this 代表 是 这个内部类的对象 拉布达表达式的 this 代表 当前的 从下面代码测试
     *  2.  内部类和兰布达表达式一样 ，当内部类使用外部的变量时必须是final的 ， 因为如果不是final的  将lambda从创建它的线程
     *          传递给其他线程，这样如果第二个线程中的lanbda可以修改局部编程，那么方才提到的竞争条件与可见性问题就会出现
     * @return
     */
    @Override
    public String toString() {
        return "Hello Word!";
    }

    public static void main(String[] args) {
        new Hello().runnable1.run();
        new Hello().runnable2.run();
        UnaryOperator<String> b = new Hello().b;
        String apply = b.apply("12");
        System.out.println(apply);
    }
}
