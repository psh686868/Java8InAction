package lambdasinaction.chap11.psh;

import java.util.concurrent.CompletableFuture;
import lambdasinaction.chap11.v1.BestPriceFinder;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMainPSH {

    private static BestPriceFinderPSH bestPriceFinderPsh = new BestPriceFinderPSH();

    public static void main(String[] args) {

//        excutor("使用一般的Stream流就行 对多个商店的 同一个产品价格：：",()->bestPriceFinderPsh.findPricesSequential("myPhone27S"));
//        excutor("使用并行流 ParallelStream流就行 对多个商店的 同一个产品价格：：",()->bestPriceFinderPsh.findPricesSequential("myPhone27S"));
//        excutor("使用CompletableFuture 异步方式 对多个商店的 同一个产品价格：：",()->bestPriceFinderPsh.findPricesFuture("myPhone27S"));
//        excutor("使用CompletableFuture 异步方式 对多个商店的 获取价格并且异步换汇率：：",()->bestPriceFinderPsh.findPricesInUSD("myPhone27S"));
 //       excutor("使用CompletableFuture 异步方式 对多个商店的 获取价格并且异步换汇率 优化：：",()->bestPriceFinderPsh.findPricesInUSD2("myPhone27S"));
       // excutor("使用CompletableFuture 异步方式 对多个商店的 获取价格并且异步换汇率 优化：：",()->bestPriceFinderPsh.findPricesInUSD3("myPhone27S"));
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        }).join();
        System.out.println("args = " + args);


    }

    private static void excutor(String msg , Supplier<List<String>> supplier){
        long startTime = System.nanoTime();
        System.out.println(supplier.get());
        long userTime = System.nanoTime() - startTime;
        System.out.println(msg+" and user Time : " +userTime+"长度" +(userTime+"").length());
    }
}
