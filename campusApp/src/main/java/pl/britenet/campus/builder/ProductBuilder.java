package pl.britenet.campus.builder;

import pl.britenet.campus.obj.model.Category;
import pl.britenet.campus.obj.model.Product;

public class ProductBuilder {
    private final Product product;

    public ProductBuilder(int id) {
        this.product = new Product(id);
    }

    public ProductBuilder setName(String name) {
        this.product.setName(name);
        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.product.setDescription(description);
        return this;
    }

    public ProductBuilder setPrice(double price) {
        this.product.setPrice(price);
        return this;
    }

    public ProductBuilder setDiscount(int discount_id) {
        this.product.setDiscountId(discount_id);
        return this;
    }

    public ProductBuilder setCategoryId(int category_id) {
        this.product.setCategoryId(category_id);
        return this;
    }

    public ProductBuilder setCategory(Category category) {
        this.product.setCategory(category);
        return this;
    }

    public Product getProduct() {
        return this.product;
    }
}
