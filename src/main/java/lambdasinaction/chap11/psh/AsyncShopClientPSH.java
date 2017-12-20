package lambdasinaction.chap11.psh;

import lambdasinaction.chap11.AsyncShop;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncShopClientPSH {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AsyncShopPSH shop = new AsyncShopPSH("BestShop");
        long start = System.nanoTime();
        Future<Double> myPhone = shop.getPrice("myPhone");
        long incocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + incocationTime + " msecs");
        try {
            System.out.println("Price is " + myPhone.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrivalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrivalTime + " msecs");

    }
}