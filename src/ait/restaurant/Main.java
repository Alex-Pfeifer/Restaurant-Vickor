// Main.java
package ait.restaurant;

import ait.restaurant.model.Client;
import ait.restaurant.model.OrderQueue;
import ait.restaurant.staff.Chef;
import ait.restaurant.staff.Waiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int chefNumber = 3;
        int waiterNumber = 5;
        int clientNumber = 20;
        int queueCapacity = 5;

        // Очередь заказов от клиентов официантам
        OrderQueue clientRequestQueue = new OrderQueue(queueCapacity);
        // Очередь заказов на кухню
        OrderQueue kitchenQueue = new OrderQueue(queueCapacity);
        // Очередь готовых заказов
        OrderQueue readyQueue = new OrderQueue(queueCapacity);

        ExecutorService chefs = Executors.newFixedThreadPool(chefNumber);
        ExecutorService waiters = Executors.newFixedThreadPool(waiterNumber);
        ExecutorService clients = Executors.newFixedThreadPool(clientNumber);

        // Стартуем поваров
        for (int i = 1; i <= chefNumber; i++) {
            chefs.submit(new Chef(i, kitchenQueue, readyQueue));
        }
        // Стартуем официантов
        for (int i = 1; i <= waiterNumber; i++) {
            waiters.submit(new Waiter(i, clientRequestQueue, kitchenQueue, readyQueue));
        }
        // Стартуем клиентов (каждый делает по одному заказу)
        for (int i = 1; i <= clientNumber; i++) {
            clients.submit(new Client(i, clientRequestQueue));
            Thread.sleep(100); // немного рандомизируем время прихода
        }

        // Ждём, пока все клиенты разместят заказы
        clients.shutdown();
        clients.awaitTermination(1, TimeUnit.MINUTES);

        // Дадим системе время на обработку всех заказов
        Thread.sleep(10000);

        // Завершаем работу поваров и официантов
        chefs.shutdownNow();
        waiters.shutdownNow();

        System.out.println("Restaurant closed.");
    }
}
