package com.multiTest.demo.SyncTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class SynchronizedEx2 {
    public synchronized void test2(int n) {
        for (int i = 0; i < 6; i++) {
            log.info("test "+n+" is "+i);
        }
    }

    public static void main(String[] args) {
        SynchronizedEx2 ex1 = new SynchronizedEx2();
        SynchronizedEx2 ex2 = new SynchronizedEx2();
        ExecutorService exService = Executors.newCachedThreadPool();
        exService.execute(() -> {
            ex1.test2(1);
        });
        exService.execute(() -> {
            ex2.test2(2);
        });
    }
}
