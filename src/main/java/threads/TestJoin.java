package threads;

/**
 * @author PSH
 * Date: 2017/12/29
 * join 是从等待通知演变而来的
 */
public class TestJoin {
    public static void main(String[] args) {
        testJoin();
    }

    public static void testJoin() {
        Thread previous = Thread.currentThread();
        for (int i=0;i<10;i++) {
           Thread thread = new Thread(new donimao(previous),"thread is " + i);
           thread.start();
           previous = thread;

        }
        System.out.println(Thread.currentThread().getName() + "：ok");
    }

    static class donimao implements Runnable{
        private final Thread preThread;
        @Override
        public void run() {
            try {
                preThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " do work");
        }
        private donimao (Thread thread) {
            preThread = thread;
        }
    }
}

