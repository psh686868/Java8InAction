package threads;

/**
 * Create by psh
 * Date: 2017/12/5
 */
public class TestDeadLock {

    private static  String a = "a";
    private static  String b = "b";

    public static void main(String[] args) {
        new TestDeadLock().deadLock();
    }

    private void deadLock() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    try {
                        Thread.sleep(2000);
                        synchronized (b) {
                            System.out.println("1");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (b) {
                    synchronized (a) {
                        System.out.println("2");
                        System.out.println(Thread.currentThread().getName());
                    }
                }
            }
        });

        thread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.start();
    }
}
