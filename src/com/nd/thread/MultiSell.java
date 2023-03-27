package com.nd.thread;

/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2023/3/27 16:37
 */
public class MultiSell {
    private static int total = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        synchronized (MultiSell.class) {
                            if (total <= 0) {
                                break;
                            }
                            System.out.println("当前线程是:" + Thread.currentThread().getName() + " 卖出1张" + " 还剩票数: " + --total);
                        }
                    }
                }
            }).start();
        }
    }
}
