package share;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author PSH
 * Date: 2018/5/16
 */
public class Foo {

    public static int age = 22;

    static {
        System.out.println("foo" + Foo.age);
    }

    public void test() {
        int a = 12;
    }

    public synchronized void test2 () {

    }

    public void test3 () {
        synchronized (this) {

        }
    }

    public static synchronized void  test4 () {
        return;
    }

    public void test5 () {
        int i = 1;
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            i++;
        } finally {
            lock.unlock();
        }
    }

}
