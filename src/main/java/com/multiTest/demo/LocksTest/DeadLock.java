package com.multiTest.demo.LocksTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadLock {
        private static String A = "A";
        private static String B = "B";

        public static void main(String[] args) {
            new DeadLock().deadLock();
        }
        private void deadLock() {
            int state = 1;
            log.info("current is :{}", state);
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (A) {
                        try { Thread.currentThread().sleep(1000); }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }synchronized (B) {
                            log.info("1"); }
                    }
                }
            });
            Thread t2 = new Thread(new Runnable(){
                @Override
                public void run() {
                    synchronized (B) {
                        synchronized (A) {
                            log.info("1"); }
                    }
                }
            });
            t1.state = 0;
            t2.stare = 0;
            //JVM线程调度t1,t2先执行哪个线程不确定。td2的run()可能在td1的run()之前运行
            t1.start();
            t2.start();
        }
}
