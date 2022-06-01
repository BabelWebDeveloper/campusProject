package pl.britenet.campus.obj.model;

public class Category {

    private final int id;
    private String name;

    private Product product;

    public Category(int id) {
        this.id = id;
    }

    public Category() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return String.format("category name: %s", name);
    }
}
