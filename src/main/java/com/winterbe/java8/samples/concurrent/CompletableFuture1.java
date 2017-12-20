package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 */
public class CompletableFuture1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();

        future.complete("hello:");
        future.thenAccept((value) -> {
            System.out.println(Thread.currentThread().getName() + ":" + value); //main hello
        }).thenAccept((value) -> {
            System.out.println(Thread.currentThread().getName() + ":" + value); //mian null 因为已经被消费了
        });




    }


}
