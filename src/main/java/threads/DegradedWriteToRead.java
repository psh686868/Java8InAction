package threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * 练习锁的降级
 * Create by psh
 * Date: 2017/12/12
 */
public class DegradedWriteToRead {
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static boolean update = false;

    public static void testLock (int index ) {
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();
        if ( !update ) {
            //先释放读锁 确保  下面从写锁降级到读锁
            //readLock.unlock();

            writeLock.lock(); //有lock的地方 必须要在finally unlock，不然会发生死锁

            try {
                if (!update) {
                    //业务逻辑
                    System.out.println("执行业务逻辑1；index: " + index);
                    //update = true;

                    //降级写锁 读锁获取 finally 释放写锁
                    readLock.lock(); //将在第二个finaly释放 锁
                }
            } finally {
                System.out.println("第一个finally；index: " + index);
                writeLock.unlock();
            }
            //写锁降级完成 变为读锁
            System.out.println("hehehehe");

            try {
                //执行业务逻辑
                System.out.println("执行业务逻辑2；index: " + index);
            } finally {
                System.out.println("第二个finally；index: " + index);
                readLock.unlock();
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 50; i++) {
            ExecutorService executors = ThreadFactoryDemo.executors;
            final int index = i;
            executors.execute(() -> {
                DegradedWriteToRead.testLock(index);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
    }
}
