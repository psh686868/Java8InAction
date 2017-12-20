package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author PSH
 * Date: 2017/12/12
 */
public class ThreadFactoryDemo {
    public static final ExecutorService executors = new ThreadPoolExecutor(20, 40,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue());

    public static void main(String[] args) {
        List<Runnable> runnableList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final int index = i;
           Runnable runnable = () -> {
               System.out.println(Thread.currentThread().getName() + ": index: " + index);
           };
           runnableList.add(runnable);
           executors.execute(runnable);
        }
        executors.shutdown(); //10个
        //executors.shutdownNow();//会出现小于10个

        //线程池关闭后再使用会报 java.util.concurrent.RejectedExecutionException
        executors.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("关闭后再使用线程池会怎么样");
            }
        });
        System.out.println("finsh");
    }
}
