package share;

/**
 * @author PSH
 * Date: 2018/5/16
 */
public class Bar extends Foo{

    public static int age = 18;

    static {
        System.out.println("foo on bar" + Foo.age);
        System.out.println("bar" + Bar.age);
    }
}
