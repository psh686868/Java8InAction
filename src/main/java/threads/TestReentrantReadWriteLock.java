package threads;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author PSH
 * Create by psh
 * Date: 2017/12/8
 */
public class TestReentrantReadWriteLock {
    private static final HashMap<String,String> hashMap = new HashMap();
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock readLock = readWriteLock.readLock();
    private static final Lock writeLock = readWriteLock.writeLock();

    public static String getData (String key) {
        long start = System.nanoTime();
        System.out.println(Thread.currentThread().getName() + " get start:" + start);
        readLock.lock();
        try {
            String value = hashMap.get(key);
            return value;
        } finally {
            System.out.println(Thread.currentThread().getName()+" get end:" + System.nanoTime());
            readLock.unlock();
        }

    }

    public static void addData(String key , String value) {
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + " add start:" + System.nanoTime());
        try {
            hashMap.put(key,value);
        } finally {
            System.out.println(Thread.currentThread().getName()+" add end:" + System.nanoTime());
            writeLock.unlock();
        }
    }


    public void clearData() {
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + " clear start:" + System.nanoTime());
        try {
            hashMap.clear();
        } finally {
            System.out.println(Thread.currentThread().getName()+" clear end:" + System.nanoTime());
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        TestReentrantReadWriteLock.addData("key","data 1 ");

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                TestReentrantReadWriteLock.getData("key");
            }).start();

        }
    }

}
