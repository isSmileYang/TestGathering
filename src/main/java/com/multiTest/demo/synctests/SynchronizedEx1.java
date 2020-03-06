package com.multiTest.demo.synctests;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedEx1 {
    // 修饰方法里的代码块
    public void test() {
        synchronized (this) {
            for (int i = 0; i < 6; i++) {
                log.info("test current is " + i );
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedEx1 ex = new SynchronizedEx1();
        //用线程池
        ExecutorService exService = Executors.newCachedThreadPool();
        //开启一个线程执行方法
        exService.execute(() -> {
            ex.test();
        });
        exService.execute(() -> {
            ex.test();
        });
    }
}
