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

    public static int age = 22;
    public static final ReadWriteLock lock = new ReentrantReadWriteLock();
    public static final Lock writeLock = lock.writeLock();
    public static final Lock readLock = lock.readLock();

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
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            age++;
        } finally {
            lock.unlock();
        }
    }

    public int writeTest () {
        writeLock.lock();
        try {
            age++;
            return age;
        } finally {
            writeLock.unlock();
        }
    }
    public int readTest () {
        readLock.lock();
        try {
            return age;
        } finally {
            readLock.unlock();
        }
    }

}
