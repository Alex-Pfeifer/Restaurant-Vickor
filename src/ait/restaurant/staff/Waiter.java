// Waiter.java
package ait.restaurant.staff;

import ait.restaurant.model.Order;
import ait.restaurant.model.OrderQueue;

public class Waiter implements Runnable {
    private final int id;
    private final OrderQueue clientRequestQueue;
    private final OrderQueue kitchenQueue;
    private final OrderQueue readyQueue;

    public Waiter(int id,
                  OrderQueue clientRequestQueue,
                  OrderQueue kitchenQueue,
                  OrderQueue readyQueue) {
        this.id = id;
        this.clientRequestQueue = clientRequestQueue;
        this.kitchenQueue = kitchenQueue;
        this.readyQueue = readyQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Принимаем заказ от клиента
                Order incoming = clientRequestQueue.take();
                System.out.println("Waiter " + id + " received " + incoming.getName());
                // Кладём в очередь на кухню
                kitchenQueue.put(incoming);
                System.out.println("Waiter " + id + " placed " + incoming.getName() + " to kitchen");

                // Берём готовый заказ
                Order ready = readyQueue.take();
                System.out.println("Waiter " + id + " delivering " + ready.getName());
            }
        } catch (InterruptedException e) {
            System.out.println("Waiter " + id + " stopped.");
        }
    }
}
