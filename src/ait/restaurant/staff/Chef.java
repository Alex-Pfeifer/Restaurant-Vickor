package ait.restaurant.staff;

public class Chef implements Runnable {
    private final int id;

    public Chef(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {

    }
}
