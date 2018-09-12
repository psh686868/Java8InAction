package lambdasinaction.chap11.psh;

import lambdasinaction.chap11.Discount;
import lambdasinaction.chap11.ExchangeService;
import lambdasinaction.chap11.Quote;
import lambdasinaction.chap11.Shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BestPriceFinderPSH {

    private final List<Shop> shops = Arrays.asList(//new Shop("BestPrice"),
//            new Shop("LetsSaveBig"),
//            new Shop("MyFavoriteShop"),
//            new Shop("BuyItAll"),
//            new Shop("BuyItAllTest1"),
//            new Shop("BuyItAllTest2"),
            new Shop("ShopEasy"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });


    /***
     * get name 获取 店铺名称
     * get price 根据传入的产品获取  价格  会停止1秒的时间
     * @param product
     * @return
     */

    //使用一般流进行计算
    public List<String> findPricesSequential(String product) {
        List<String> collect = shops.stream().map(shop -> shop.getPrice(product))
                .collect(Collectors.toList());
        return collect;
    }

    /**
     * parallel 并行的，水平线
     * 使用并行流进行计算 ，因为牵扯的数据 可以进行批处理
     * 优点：提高数据处理能力
     * 缺点： 如果牵扯阻塞式则不是很好
     * 即：适合cpu密集型的任务
     */

    public List<String> findPricesParallel(String product) {
        List<String> collect = shops.parallelStream().map(shop -> shop.getName() + shop.getPrice(product))
                .collect(Collectors.toList());


        Stream<CompletableFuture<String>> completableFutureStream = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is "
                        + shop.getPrice(product), executor));
        return collect;
    }

    /***
     * 使用completableature 进行一步
     *
     */

    public List<String> findPricesFuture(String product) {
        //获取所有的feature对象 ，然后对feature进行join操作
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName()
                        + shop.getPrice(product), executor))
                .collect(Collectors.toList());
        //
        List<String> collect = futures.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());
        return collect;

    }

    /***
     * thenCombine 使用的场景是 ： 你需要将两个完全不相干的Com-pletableFuture对象的结果整合起来，
     *                          而且你也不希望等到第一个任务完全结束才开始第二项任务
     * 如：，有一家商店提供的价格是以欧元（EUR）计价的，
     *     但是你希望以美元的方式提供给你的客户。
     *     你可以用异步的方式向商店查询指定商品的价格，
     *     同时从远程的汇率服务那里查到欧元和美元之间的汇率。
     *     当二者都结束时，再将这两个结果结合起来，
     *     用返回的商品价格乘以当时的汇率，得到以美元计价的商品价格。
     *     用这种方式，你需要使用第三个Com-pletableFuture对象，
     *     当前两个CompletableFu-ture计算出结果，并由BiFunction方法完成合并后，由它来最终结束这一任务
     *
     *     两种方式：不使用completableFeature的方式 在 web 844作为对比 但是这种方法更加方便 ，函数式更加流畅，
     *
     * @param product
     * @return
     */
    public List<String> findPricesInUSD(String product) {
        // 主要是两种方式 1. 异步获取价格 比如为美元，2 异步获取汇率 3 获取的美元价格通过汇率计算出
        List<CompletableFuture> futures = new ArrayList<>();
         int i =3;
        shops.stream().forEach(shop -> {

            CompletableFuture<Double> completableFuture = CompletableFuture.supplyAsync(() -> {

                return shop.calculatePrice(product);
            })
                    .thenCombine(
                            CompletableFuture.supplyAsync(() -> {
                                System.out.println("测试2");
                                return ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD);
                            }),
                            (price, rate) -> {
                                System.out.println("测试3");
                                return price * rate;
                            }
                    );
            futures.add(completableFuture);
        });

        List<String> collect = futures.stream().map(CompletableFuture::join)
                .map(price -> " price is " + price)
                .collect(Collectors.toList());
        System.out.println(collect);

        i = 4;
        return collect;
    }

    public Double findPricesInUSDJava7(String prodcuct) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Double>> listPrice = new ArrayList<>();
        for (Shop shop : shops) {

            final int a = 5;

            final Future<Double> futureRate = executor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    System.out.println("tast 1" + Thread.currentThread().getName() + a);
                    return ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD);
                }
            });


            Future<Double> price = executor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    System.out.println("test 2" + Thread.currentThread().getName() + "####" + a);
                    Double price1 = shop.calculatePrice(prodcuct);
                    return price1 * futureRate.get();
                }
            });

            listPrice.add(price);
            return 1.0;
        }
        return null;
    }

    /***
     * 对 findPricesInUSD 进行进一步的优化
     * @param product
     * @return
     */
    public List<String> findPricesInUSD2(String product) {
        List<CompletableFuture<String>> shopFutures = new ArrayList<>();

        for (Shop shop : shops) {
            CompletableFuture<String> stringCompletableFuture =
                    CompletableFuture.supplyAsync(() -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD))
                            .thenCombine(
                                    CompletableFuture.supplyAsync(
                                            () -> shop.calculatePrice(product)),
                                    (price, rate) -> {
                                        System.out.println(price + "!!!!1");
                                        return price * rate;
                                    }
                            ).thenApply(price -> shop.getName() + price);
            shopFutures.add(stringCompletableFuture);
        }

        List<String> collect = shopFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        return collect;
    }

    /***
     * 对 findPricesInUSD2 进行进一步的优化 将所有的东西进行 流操作
     * @param product
     * @return
     */
    public List<String> findPricesInUSD3(String product) {
        List<CompletableFuture<String>> shopFutures = new ArrayList<>();

        if (shops == null || shops.isEmpty()) {
            return Collections.emptyList();
        }

        Stream<CompletableFuture<String>> completableFutureStream = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.calculatePrice(product))
                        .thenCombine(
                                CompletableFuture.supplyAsync(() -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)),
                                (price, rate) -> price * rate)
                        .thenApply((price) -> shop.getName() + " price is " + price));
        /** 注意： 合并时一定要是 completable的集合 而不是 流 */
        List<CompletableFuture<String>> collect = completableFutureStream.collect(Collectors.toList());
        List<String> prices = collect.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());
        return prices;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BestPriceFinderPSH bestPriceFinderPSH = new BestPriceFinderPSH();
        List<String> myPhone27S = bestPriceFinderPSH.findPricesInUSD2("myPhone27S");
        System.out.println(myPhone27S);
        //for (int i = 0; i < 50; i++) {
        //    System.out.println("@@@@@@@@start");
        //    Double myPhone27S = bestPriceFinderPSH.findPricesInUSDJava7("myPhone27S");
        //    System.out.println(myPhone27S+"!!!!!");
        //    System.out.println("@@@@@@@@end");
        //}
    }

}
