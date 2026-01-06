import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadSafeCollectionsExample {

    public static void main(String[] args) throws InterruptedException {

        List<String> orderList = new CopyOnWriteArrayList<>();

        Thread t1 = new Thread(() -> { addOrder(orderList, "Order 1 ");
        }, "Thread 1");
        Thread t2 = new Thread(() -> { addOrder(orderList, "Order 2 ");
        }, "Thread 2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final order list: " + orderList);
    }

    public static void addOrder(List<String> orderList, String order) {
        orderList.add(order);
        System.out.println(Thread.currentThread().getName() + " added order: " + order);
    }
}
