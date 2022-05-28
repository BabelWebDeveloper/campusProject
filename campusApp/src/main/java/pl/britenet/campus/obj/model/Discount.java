package pl.britenet.campus.obj.model;

public class Discount {
    private int id;
    private int percent;
    private String description;

    private Category category;
    private Product product;

    public Discount(int id) {
        this.id = id;
    }
    public Discount() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        try {
            return String.format("produkt: %s, kategoria: %s, opis promocji: %s", product.getName(), category.getName(), description);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
