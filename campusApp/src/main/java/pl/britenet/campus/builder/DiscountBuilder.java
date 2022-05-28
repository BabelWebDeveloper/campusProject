package pl.britenet.campus.builder;

import pl.britenet.campus.obj.model.Category;
import pl.britenet.campus.obj.model.Discount;
import pl.britenet.campus.obj.model.Product;

public class DiscountBuilder {
    private final Discount discount;

    public DiscountBuilder(int id) {
        this.discount = new Discount(id);
    }

    public DiscountBuilder setDiscountPercent(int discount_percent) {
        this.discount.setPercent(discount_percent);
        return this;
    }

    public DiscountBuilder setDescription(String description) {
        this.discount.setDescription(description);
        return this;
    }

    public DiscountBuilder setCategory (Category category) {
        this.discount.setCategory(category);
        return this;
    }

    public DiscountBuilder setProduct (Product product) {
        this.discount.setProduct(product);
        return this;
    }

    public Discount getDiscount() {
        return this.discount;
    }
}
