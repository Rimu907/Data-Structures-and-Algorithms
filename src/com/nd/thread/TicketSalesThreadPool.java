import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicketSalesThreadPool {
    public static void main(String[] args) {
        // 创建一个线程池，包含10个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        // 创建售票口对象
        TicketOffice ticketOffice = new TicketOffice();

        // 提交10个任务到线程池中
        for (int i = 0; i < 10; i++) {
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    while (ticketOffice.getRemainingTickets() > 0) {
                        int num = Math.min(10, ticketOffice.getRemainingTickets());
                        ticketOffice.sellTickets(num);
                        try {
                            Thread.sleep(100); // 模拟售票时间
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        // 关闭线程池
        threadPool.shutdown();
    }
}

class TicketOffice {
    private int ticketsSold;

    public synchronized void sellTickets(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Number of tickets to sell must be greater than 0");
        }

        if (num > getRemainingTickets()) {
            throw new IllegalArgumentException("Number of tickets to sell exceeds the remaining tickets");
        }

        ticketsSold += num;
        System.out.println(Thread.currentThread().getName() + " sold " + num + " tickets. Total tickets sold: " + ticketsSold);
    }

    public synchronized int getRemainingTickets() {
        return 100 - ticketsSold;
    }
}
