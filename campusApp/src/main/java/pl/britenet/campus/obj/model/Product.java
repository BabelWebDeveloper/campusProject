package pl.britenet.campus.obj.model;

public class Product {

    private final int id;
    private String name;
    private String description;
    private double price;
    private int discountId;

    private int categoryId;

    private Category category;

    public Product(int id) {
        this.id = id;
    }
    public Product() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("name: %s, description: %s, price: %f, discount_id: %s, category_id: %s", name, description, price, discountId, categoryId);
    }

}
