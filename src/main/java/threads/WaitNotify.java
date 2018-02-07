package threads;

import java.time.LocalTime;

/**
 * @author PSH
 * Date: 2018/1/4
 */
public class WaitNotify {
    static Object lock = new Object(); //两个线程的通信对象
    static boolean flag = true;

    public static void main(String[] args) throws Exception {

        new Thread(new WaitThread(),"WaitThread").start();

        Thread.sleep(500);

        new Thread(new NotifyThread(),"NotifyThread").start();
    }


    static class  WaitThread implements Runnable{
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    System.out.println(LocalTime.now() + "; " + Thread.currentThread());
                    try {
                        lock.wait();  //释放锁了
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(LocalTime.now() + "; " + Thread.currentThread().getName() + "waitThread finnsh");
            }
        }
    }

    static class NotifyThread implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(LocalTime.now() + "; " + Thread.currentThread().getName() + " do");
                lock.notify();

                flag = false;

                System.out.println(LocalTime.now() + "; " + Thread.currentThread().getName() + " do2");
                try {
                    Thread.sleep(500);
                    System.out.println(LocalTime.now() + "; " + Thread.currentThread().getName() + " do3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {

                System.out.println(LocalTime.now() + "; " + Thread.currentThread().getName() + " finnsh");
            }
        }
    }
}



