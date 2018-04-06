package com;


public class TestLock {
    private static volatile int a = 0;
    private static int b = 0;


    public  void test() {
        final TestLock testLock = new TestLock();

        for (int i = 0; i <200; i++) {
            TestLock.b++;
            new Thread(() -> testLock.app()).start();
        }
        System.out.println(testLock.a == testLock.b);
        System.out.println(testLock.b);
        System.out.println(testLock.a);

    }

    public synchronized  void app() {
        TestLock.a++;
    }

}
