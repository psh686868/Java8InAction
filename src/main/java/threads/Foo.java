package threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * @author PSH
 * Date: 2018/5/16
 */
public class Foo {

    public synchronized void test1 () {



    }

    public void test2 () {
        synchronized (this) {

        }
    }

    public static synchronized void  test3 () {
        return;
    }


}
