package vm.test;


/**
 * @author PSH
 * Date: 2018/3/26
 */
public class Foo {
    synchronized static void bar () {

    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        Class<?> clz = foo.getClass();
        System.out.println(clz == Foo.class);
        Class<?> clzClass = clz.getClass();
        System.out.println(clzClass == Class.class);
    }
}
