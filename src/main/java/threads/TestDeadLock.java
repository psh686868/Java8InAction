package threads;


/**
 * Create by psh
 * Date: 2017/12/5
 */
public class TestDeadLock {

    private static  String a = "a";
    private static  String b = "b";

    public static void main(String[] args) {
        TestJoin testJoin = new TestJoin();
        Byte[] aByte = new Byte[10];
//        synchronized (a) {
//            System.out.println(a);
//        }
//        new TestDeadLock().deadLock(20);
    }

    private int deadLock(int param) {
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
        },"Thread one");

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
        },"thread two");

        thread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.start();

        return param;
    }
}
