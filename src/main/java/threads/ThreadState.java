package threads;

import java.util.concurrent.TimeUnit;

/**
 * @author PSH
 * Date: 2018/1/2
 * 线程的生命周期分为6种状态 ， 我们可以用 jstack工具查看着6种状态
 * <p>
 * NEW 初始状态，              线程被创建，但是还没有调用start()方法
 * RUNNABLE 运行状态 ，        java线程将操作系统的运行和就绪状态笼统的称作为 "运行中"
 * BLOCKED 阻塞状态 ，         表示线程阻塞于锁
 * WATING 等待状态，           表示线程进行入等待状态，进入该状态表示线程需要等待其他线程做出一些特定动作（通知或者中断）
 * TIME_WAITING 超时等待状态   改状态不同于WAITNG状态，它是可以在指定的时间自行返回的
 * TERMINATED 终止状态         终止状态，表示当前线程已经执行完毕
 * <p>
 * 下面将模拟这几种状态，然后通过jstack查看线程的状态
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new TIMEWAITING(),"TIMEWAITING-Thread").start();
        new Thread(new WATING(),"WATING-Thread").start();

        //这两个线程一个获取类锁实列，一个获取不了
        new Thread(new Blocked(),"BLOCK-Thread1").start();
        new Thread(new Blocked(),"BLOCK-Thread2").start();
    }

    //该线程不断的进行睡眠      TIMED_WAITING (sleeping)
    static class TIMEWAITING implements Runnable {

        @Override
        public void run() {
            while (true) {
                second(100);
            }
        }
    }

    // 该线程在Waiting.class实列上等待   WAITING (on object monitor)
    static class WATING implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (WATING.class) {
                    try {
                        WATING.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //该线程在Blocked.class实列上等待，不会释放该资源 一个状态 BLOCKED (on object monitor) 另一个 ThreadState.java:65 TIMED_WAITING (sleeping)

    static class Blocked implements Runnable {

        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    second(100);
                }
            }

        }
    }


    public static final void second(long second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
