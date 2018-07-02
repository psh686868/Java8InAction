package threads;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Pattern;
import javax.xml.ws.Holder;

public class TimerTaskUtil {

    /**
     * @author 肖长路
     * @description 定时任务自定义线程池创建线程
     * @date 2018/6/1 15:46
     */
    private static class TimerTaskThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("Test-Thread: " + count.addAndGet(1));
            System.out.println("当前线程的名称:" + thread.getName());
            return thread;
        }
    }

    /**
     * @author 肖长路
     * @description 自定义阻塞  处理机制
     * @date 2018/6/1 16:15
     */
    private static class TimerTaskRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * JVM可用处理器的个数
     */
    private static final int cores = Runtime.getRuntime().availableProcessors();
    private static ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(cores,
        new TimerTaskThreadFactory(), new TimerTaskRejectedExecutionHandler());

    /**
     * 在指定的延时之后开始以固定的频率来运行任务。
     * 后续任务的启动时间不受前次任务延时影响。
     * 已固定的频率来执行某项计划(任务)
     * 上一个任务开始执行之后延迟设定时间再执行，是从上一个任务开始时计时，
     * 但对于运行时长超过延迟时长的任务，会等上一个任务执行完之后，下一个任务才开始执行，
     * 此时，延时没有任何意义
     *
     * @param task 具体待执行的任务
     * @param initialDelay 首次执行任务的延时时间
     * @param period 每次执行任务的间隔时间(单位秒)
     * @param unit 时间单位
     */
    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        return scheduled.scheduleAtFixedRate(task, initialDelay, period, unit);
    }

    /**
     * 在指定的延时之后启动，两次任务间保持固定的时间间隔
     * 相对固定的延迟后，执行某项计划。
     * 上一个任务结束执行之后延迟设定时间再执行，
     * 是从上一个任务结束时开始计算
     *
     * @param task 具体待执行的任务
     * @param initialDelay 首次执行任务的延时时间
     * @param period 两次任务的间隔时间(单位秒)
     * @param unit 时间单位
     */
    public static ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long initialDelay, long period,
        TimeUnit unit) {
        return scheduled.scheduleWithFixedDelay(task, initialDelay, period, unit);
    }

    //    public static void main(String[] args) {
//
//        String patternStr = "^((http://)|(https://)|(ftp://)|(//))";
//        String patternStr2 = "^[0-9]{8}/((bmp)|(dib)|(gif)|(jfif)|(jpeg)|(jpg)|(png)|(tif)|(tiff)|(ico))/.+(bmp|dib|gif|jfif|jpeg|jpg|png|tif|tiff|ico)";
//        String patternStr3 = "^[0-9]{4,8}/((bmp)|(dib)|(gif)|(jfif)|(jpeg)|(jpg)|(png)|(tif)|(tiff)|(ico))/.+(.bmp|dib|gif|jfif|jpeg|jpg|.png|tif|tiff|ico)$";
//
//
//        Pattern p = Pattern.compile(patternStr2,Pattern.CASE_INSENSITIVE);
//        if (p.matcher("20161206/PNG/46677d060e8eaaaccd91de684842828f.png").matches()) {
//            //System.out.println("p = " + p);
//        }
//
//        int a = 2;
//        increate(a);
//        System.out.println("a = " + a);
//
////        System.out.println("当前系统时间："+System.currentTimeMillis());
////
////        Long startTime = System.nanoTime();
////        System.out.println("执行任务时间："+startTime);
////
////        Long sjc = startTime-System.currentTimeMillis();
////        System.out.println("执行的时间差："+sjc);
////
////        TimeUnit sjdw = TimeUnit.MILLISECONDS;
////
////        scheduleAtFixedRate(new Runnable() {
////            @Override
////            public void run() {
////                System.out.println(Thread.currentThread().getName() + "  $$$$$$$$$$$$$" + "11111111111111");
////
////
////            }
////        },1,50,sjdw);
//    }
    public static int increate(int a) {
        ++a;
        return a;
    }


    public static void main(String[] args) {
        Map map =new HashMap();
        Map map2 =new HashMap();
        List<String>list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        map.put("1","Ram");
        map.put("2","Ganesh");
        map.put("3","Paul");


        map2.putAll(map);
        map2.put("4", Arrays.asList("1","dfda","fdsafas"));
        map2.put("5", true);
        System.out.println("map2 = " + map2);
//        Function<Integer, Integer> adder = adder();
//        Integer sum1 = adder.apply(1);
//        Integer sum2 = adder.apply(2);
//        Integer sum3 = adder.apply(3);
//        Function<Integer, Integer> adder2 = adder();
//        Integer adder2Sum1 = adder2.apply(1);
//        System.out.println("sum1 = " + sum1 + "; sum2 = " + sum2 + "; sum3 = " + sum3 + "; adder2Sum1 = " + adder2Sum1);
    }

    public static Function<Integer, Integer> adder() {
        final Holder<Integer> sum = new Holder<>(0);
        return (Integer value) -> {
            sum.value +=  value;
            return sum.value;
        };
    }


}

