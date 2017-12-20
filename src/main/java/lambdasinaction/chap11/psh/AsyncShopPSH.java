package lambdasinaction.chap11.psh;

import common.GlobalConstant;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static lambdasinaction.chap11.Util.delay;
import static lambdasinaction.chap11.Util.format;

public class AsyncShopPSH {

    public final Executor executor = GlobalConstant.GlobalThreadPool.getExecutor();

    private final String name;
    private final Random random;

    public AsyncShopPSH(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public Future<Double> getPrice(String product) {

        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
       // if (true) throw new RuntimeException("product not available");
        double format = format(random.nextDouble() * product.charAt(0) + product.charAt(1));
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

}