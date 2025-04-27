// Chef.java
package ait.restaurant.staff;

import ait.restaurant.model.Order;
import ait.restaurant.model.OrderQueue;

public class Chef implements Runnable {
    private final int id;
    private final OrderQueue kitchenQueue;
    private final OrderQueue readyQueue;

    public Chef(int id, OrderQueue kitchenQueue, OrderQueue readyQueue) {
        this.id = id;
        this.kitchenQueue = kitchenQueue;
        this.readyQueue = readyQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = kitchenQueue.take();
                System.out.println("Chef " + id + " is cooking " + order.getName());
                Thread.sleep((int)(Math.random() * 5000));
                readyQueue.put(order);
                System.out.println("Chef " + id + " finished " + order.getName());
            }
        } catch (InterruptedException e) {
            System.out.println("Chef " + id + " stopped.");
        }
    }
}
