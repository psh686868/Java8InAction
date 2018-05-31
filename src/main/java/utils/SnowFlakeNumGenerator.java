package utils;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @author PSH
 * Date: 2018/5/2
 * twitter 的雪花算法
 *  原理：64 bit 一次  【 0  时间搓（41 bit）  机械id   序列号（12位 保证在每毫秒内同一机器生成的自增 数字）
 *  优点：生成的id 具有趋势递增
 *  缺点：对系统的时钟要求比较高 系统时钟回拨可能会 id乱序 或者冲突
 *
 */
public class SnowFlakeNumGenerator {
    private Long lastTime;
    private Long serialNum = 1L;
    private Long workerILd;

    /**
     * 生成序列号
     */
    private synchronized Long generatorSerialNum () {
        Long currentTtime = System.nanoTime();

        /**
         * 如果 上一毫秒数 > 当前毫秒数 说明时钟回拨，cpu空转等待
         */
        if (lastTime > currentTtime) {
            while (lastTime > currentTtime) {
                currentTtime = System.nanoTime();
            }
        }
        return 1L;


    }



    final static Queue<String> queue = new ConcurrentLinkedQueue<>();
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            final int num = i;
            new Thread(() -> {
                queue.add("thread : do=" + num);
                countDownLatch.countDown();
            }).start();
        }

        while (countDownLatch.getCount() != 0) {

        }
        System.out.println(SnowFlakeNumGenerator.queue);
    }
}
