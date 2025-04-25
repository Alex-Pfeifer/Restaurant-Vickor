package ait.restaurant.model;

import java.util.LinkedList;
import java.util.Queue;

// Если BlockingQueue использовать не хочется, то можно использовать это:
public class OrderQueue {
    private final Queue<Order> queue = new LinkedList<>();
    private final int capacity;

    public OrderQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(Order order) throws InterruptedException {
        while (queue.size() >= capacity) {
            wait(); // ждем, пока освободится место
        }
        queue.add(order);
        notifyAll(); // оповещаем, что заказ добавлен
    }

    public synchronized Order take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // ждем, пока появится заказ
        }
        Order order = queue.poll();
        notifyAll(); // оповещаем, что место освободилось
        return order;
    }
}
