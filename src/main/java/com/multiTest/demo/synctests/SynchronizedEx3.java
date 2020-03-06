package com.multiTest.demo.synctests;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class SynchronizedEx3 {
    //用反射达到修饰类并调用执行的目的
    public static void test(int n) {
        synchronized (SynchronizedEx3.class) {
            for (int i = 0; i < 6; i++) {
                log.info("test"+n+"is"+i );
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedEx3 ex1 = new SynchronizedEx3();
        SynchronizedEx3 ex2 = new SynchronizedEx3();
        ExecutorService exService = Executors.newCachedThreadPool();
        exService.execute(() -> {
            ex1.test(1);
        });
        exService.execute(() -> {
            ex2.test(2);
        });
    }
}
