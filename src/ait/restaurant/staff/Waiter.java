package ait.restaurant.staff;

public class Waiter implements Runnable {
    private final int id;

    public Waiter(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {

    }
}