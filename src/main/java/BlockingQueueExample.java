import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);

        Thread producer = new Thread(() -> {
            try {
                produce(queue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Producer Thread");

        Thread consumer = new Thread(() -> {
            try {
                consume(queue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Consumer Thread");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

    public static void produce(BlockingQueue<Integer> queue) throws InterruptedException {

        for (int i = 0; i <= 5; i++){
            try {
                queue.put(i);
                System.out.println("Produced: " + i);
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void consume(BlockingQueue<Integer> queue) throws InterruptedException {

        for (int i = 0; i <= 5; i++){
            try {
                int value = queue.take();
                System.out.println("Consumed: " + value);
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
