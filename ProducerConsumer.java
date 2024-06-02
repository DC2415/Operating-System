import java.util.*;

public class ProducerConsumer{
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(5); // Buffer size is 5
        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}
class BoundedBuffer {
    private LinkedList<Integer> buffer = new LinkedList<>();
    private int maxSize;

    public BoundedBuffer(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (buffer.size() == maxSize) {
            wait(); // Buffer is full, wait for consumers
        }
        buffer.add(item);
        System.out.println("Produced: " + item);
        notify(); // Notify consumers that there are items to consume
    }

    public synchronized int consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait(); // Buffer is empty, wait for producers
        }
        int item = buffer.removeFirst();
        System.out.println("Consumed: " + item);
        notify(); // Notify producers that there is space in the buffer
        return item;
    }
}

class Producer implements Runnable {
    private BoundedBuffer buffer;

    public Producer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.produce(i);
                Thread.sleep((long) (Math.random() * 100)); // Simulate some processing time
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private BoundedBuffer buffer;

    public Consumer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.consume();
                Thread.sleep((long) (Math.random() * 100)); // Simulate some processing time
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
