package vm.test.JavaVmInAction;

/**
 * Date: 2018/2/7
 *
 *     https://www.jianshu.com/p/c14364f72b7e
 *    1、同一个字符串常量，在常量池只有一份副本；
 *    2、通过双引号声明的字符串，直接保存在常量池中；
 *    3、如果是String对象，可以通过String.intern方法，把字符串常量保存到常量池中；
 */
public class StringInternTest {
    public static void main(String[] args) {
        String a = new StringBuilder("test").append("String").append("Intern").toString();
        System.out.println(a.intern() == a);


    }
}
