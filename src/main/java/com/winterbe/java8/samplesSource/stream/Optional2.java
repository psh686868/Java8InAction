package com.winterbe.java8.samplesSource.stream;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Examples how to avoid null checks with Optional:
 *
 * http://winterbe.com/posts/2015/03/15/avoid-null-checks-in-java/
 *
 * @author Benjamin Winterberg
 */
public class Optional2 {

    static class Outer {
        Nested nested = new Nested();

        public Nested getNested() {
            return nested;
        }
    }

    static class Nested {
        Inner inner = new Inner();

        public Inner getInner() {
            return inner;
        }
    }

    static class Inner {
        String foo = "boo";

        public String getFoo() {
            return foo;
        }
    }

    public static void main(String[] args) {
        test1();
        //test2();
        //test3();
    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        }
        catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    private static void test3() {
        Outer outer = new Outer();
        resolve(() -> outer.getNested().getInner().getFoo())
                .ifPresent(System.out::println);
    }

    private static void test2() {
        Optional.of(new Outer())
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getFoo)
                .ifPresent(System.out::println);
    }

    private static void test1() {
        Optional.of(new Outer())
                .flatMap(o -> {
                    System.out.println(o.nested);
                    return Optional.ofNullable(o.nested);
                })
                .flatMap(n -> {
                    System.out.println(n.inner);
                    return Optional.ofNullable(n.inner);
                })
                .flatMap(i -> {
                    System.out.println(i.foo);
                    return Optional.ofNullable(i.foo);
                })
                .ifPresent(System.out::println);
    }
}
