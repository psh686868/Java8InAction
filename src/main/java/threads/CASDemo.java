package threads;

import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;

/**
 * @author PSH
 * Date: 2018/4/23
 * 理解CAS
 */
public class CASDemo {
    private  int value;
    private volatile int i = 1;
    private int j = 1;
    private static final Unsafe unsafe = Unsafe.getUnsafe();

    public CASDemo(int value) {
        this.value = value;
    }

    private boolean compareAndSet(int old, int update) {
        for (; ; ) { //   模拟自旋
            if (old == value) {
                value = update;
                return true;
            } else {
                continue;
            }
        }

    }

    private boolean reset(int value) {

        this.value = value;
        return true;
    }

    private void increament() {
        j++;

        compareAndSet(1, 2);
        i++;
        if (i == 5000) {
            System.out.println("i is" + i + "; j is :" + j);
        }
        reset(1);


    }


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5000);
        CASDemo casDemo = new CASDemo(1);
        for (int i = 5000; i > 0; i--) {
            new Thread(() -> {
                casDemo.increament();
                countDownLatch.countDown();
            }).start();
        }
        boolean flag = true;
       while (flag) {
          if (countDownLatch.getCount() == 0) {
              flag = false;
          }
       }
        System.out.println("主线程结束");
    }


}
