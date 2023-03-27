package com.nd.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSell {
    private static int total = 100;
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        synchronized (ThreadPoolSell.class) {
                            if (total <= 0) {
                                break;
                            }
                            total -= 1;
                            System.out.println(String.format("当前线程%s, 卖出1张票, 还剩余%d张票", Thread.currentThread().getName(), total));
                            try {
                                Thread.sleep(100); // 模拟售票时间
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
        threadPool.shutdown();
    }
}
