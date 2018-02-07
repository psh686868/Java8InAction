package threads;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Create by psh
 * Date: 2018/1/5
 */
public class DefaultThreadFactory<Job extends Runnable> implements ThreadPool<Job> {
    //核心线程池
    private volatile  int corePollSize;
    // 线程池的默认数量
    private volatile int maximumPoolSize;

    //任务队列
    //private List<Job> jobs = Collections.synchronizedList(new LinkedList<Job>());
    private LinkedList<Job> jobs = new LinkedList<>();

    //工作者列表
    //private List<Work> works = Collections.synchronizedList(new LinkedList<Work>());//问题1
    private List<Work> works = new LinkedList<Work>();//问题1


    //线程的编号

    private AtomicInteger threadNum = new AtomicInteger();

    // 构造线程的启动

    DefaultThreadFactory(int corePollSize) {
        this.corePollSize = corePollSize;
        initializeWorkers(corePollSize);
    }


    // 初始化线程工作者
    private void initializeWorkers(int corePollSize) {
        IntStream.range(0,corePollSize)
                .forEach(value -> {
                    //synchronized ()
                    Work work = new Work();
                    Thread thread = new Thread(work, "factory-pool-" + threadNum.incrementAndGet());
                    this.works.add(work);
                    thread.start();
                });
    }

    // 执行job
    @Override
    public void excutor(Job job) {

        Optional.ofNullable(job)
                .ifPresent(job1 -> {
                    synchronized (jobs) {
                        jobs.add(job);
                        //唤醒工作线程，因为工作线程的状态为    java.lang.Thread.State: WAITING (on object monitor)
                        jobs.notifyAll();
                    }
                });
    }

    // 关闭线程
    @Override
    public void shutdown() {
        works.stream().forEach(work -> {
            work.isWork = false;
        });
    }

    // 添加工作线程
    @Override
    public void addWorks(int num) {
        //添加工作线程时锁起任务
        synchronized (jobs) {
            initializeWorkers(num);
        }
    }

    // 移除工作线程
    @Override
    public void removeWorkers(int num) {

        synchronized (jobs) {
            if (num > works.size()) {
                throw new IllegalArgumentException("num > works");
            }
            works.stream().limit(num).forEach(work -> {
                work.isWork = false;
            });
        }
    }

    // 获取工作队列
    @Override
    public int jobs() {
        return 0;
    }

    //负责消费任务
     class Work implements Runnable {
        //是否工作
        private volatile boolean isWork = true;
        @Override
        public void run() {
            while (isWork) {
                Job job = null;
                synchronized (jobs) { //这里之所以要加锁就是从工作队列里取出工作线程
                    while (jobs.isEmpty()) { //工作队列为空时等待
                        try {
                            jobs.wait(); //新城状态为 WAITING
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                   // job = jobs.remove(jobs.size()-1);
                    job = jobs.removeLast();
                }
                if (job != null) {
                    job.run();
                }
            }
        }

        //关闭此工作线程
        public void shutdown () {
            isWork = false;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(800);
        executorService.execute(() -> {
            System.out.println(1);
        });
//        DefaultThreadFactory defaultThreadFactory = new DefaultThreadFactory(5);
//        for (int i = 0; i < 50; i++) {
//            final int index = i;
//            defaultThreadFactory.excutor(() -> {
//                System.out.println(Thread.currentThread().getName()+" do task: " + index );
//            });
//        }
        Thread.sleep(10);
        System.out.println("main");

    }
}


