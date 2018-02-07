package threads;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * practice ScheduledThreadPoolExecutor
 * @author PSH
 * Date: 2017/12/15
 */
public class PracticeScheduledThreadPoolExecutor {
    private static final ScheduledExecutorService scheduledExecutorService =
            Executors.newScheduledThreadPool(10);

    private static final AtomicInteger atomicInteger = new AtomicInteger (0);

    private static CountDownLatch countDownLatch = new CountDownLatch(30);

    public static  int count = 5;

    public static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static final Lock writeLock = readWriteLock.writeLock();


    public static void main(String[] args) {
        //test1();
        //test2();
        test3();

    }

    public static void test1 () {
        System.out.println("threadName: " + Thread.currentThread().getName() + " currentTime:" + System.nanoTime());
        IntStream.range(0,20).forEach(value -> {
            scheduledExecutorService.schedule(() -> {
                int i = atomicInteger.incrementAndGet();

                System.out.println("threadName: " + Thread.currentThread().getName() + " currentTime:" + System.nanoTime() +
                        "and atomicInteger is : " + i);

                //countDownLatch.countDown();

                writeLock.lock();

                try {
                    count = count +5;
                    System.out.println("count is :" + count);
                } finally {
                    countDownLatch.countDown();
                    writeLock.unlock();
                }

            } , 5, TimeUnit.SECONDS);
        });

        long count = countDownLatch.getCount();
        while (count != 0) {
            count = countDownLatch.getCount();
        }

        System.out.println("mian : " + atomicInteger.get());
    }

    public static void test2 () {
        DefaultEventExecutorGroup defaultEventExecutorGroup = new
                DefaultEventExecutorGroup(10);
         defaultEventExecutorGroup.schedule(() -> {

             System.out.println(" 6秒后执行 currentThread : " + Thread.currentThread().getName());
         },6,TimeUnit.SECONDS);
    }

    public static void test3 () {
        DefaultEventExecutorGroup defaultEventExecutorGroup = new
                DefaultEventExecutorGroup(10);
        ScheduledFuture<?> scheduledFuture = defaultEventExecutorGroup.scheduleAtFixedRate(() -> {

            int i = atomicInteger.incrementAndGet();
            if (i == 1) {
                System.out.print("6秒后执行 然后");
            }

            System.out.println("每隔10秒执行 直到scheduleFuture被取消  currentThread : "
                    + Thread.currentThread().getName());
            countDownLatch.countDown();
        }, 6, 10, TimeUnit.SECONDS);

        boolean flag = true;

        while (flag) {
            if (countDownLatch.getCount() == 10) {
                flag = false;
                scheduledFuture.cancel(!flag);
            }

        }

        Map<String,String> map = new HashMap<>(8);

    }
}
