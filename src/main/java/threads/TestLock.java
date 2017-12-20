package threads;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    //private  AtomicInteger a = new AtomicInteger(0);
    private  int a = 0;
    private  int b = 0;


    public  static void main(String... args) throws InterruptedException {
        final TestLock testLock = new TestLock();

        for (int i = 0; i <500; i++) {
            testLock.b++;
            Thread thread = new Thread(() -> testLock.app());
            thread.start();
            thread.join();

        }
        System.out.println(testLock.a);
        System.out.println(testLock.b);
        System.out.println(testLock.a);

    }

    public  void app() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            a++;
        }finally {
            lock.unlock();
        }

    }

}
