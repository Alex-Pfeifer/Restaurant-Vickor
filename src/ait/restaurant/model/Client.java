package ait.restaurant.model;

public class Client implements Runnable {
    private final int id;

    public Client(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {

    }
}