// Client.java
package ait.restaurant.model;

public class Client implements Runnable {
    private final int id;
    private final OrderQueue clientRequestQueue;

    public Client(int id, OrderQueue clientRequestQueue) {
        this.id = id;
        this.clientRequestQueue = clientRequestQueue;
    }

    @Override
    public void run() {
        Order order = new Order("Order#" + id);
        try {
            clientRequestQueue.put(order);
            System.out.println("Client " + id + " placed " + order.getName());
        } catch (InterruptedException e) {
            System.out.println("Client " + id + " was interrupted.");
        }
    }
}
