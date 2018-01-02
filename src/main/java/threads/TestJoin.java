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
        Thread thread = Thread.currentThread();
        for (int i=0;i<10;i++) {
            new Thread(new donimao(thread),"thread is " + i).start();
        }
    }

    static class donimao implements Runnable{
        private final Thread preThread;
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " do work");
        }
        private donimao (Thread thread) {
            preThread = thread;
        }
    }
}

