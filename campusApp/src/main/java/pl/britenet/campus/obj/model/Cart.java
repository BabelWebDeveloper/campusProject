package pl.britenet.campus.obj.model;

public class Cart {
    private int id;
    private int customerId;

    private double total_cost;

    private boolean isOrdered;

    public Cart(int id) {
        this.id = id;
    }
    public Cart() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public boolean isOrdered() {
        return isOrdered;
    }



    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }

    @Override
    public String toString() {
        return String.format("customer_id: %d, idOrdered: %b", customerId, isOrdered);
    }
}
