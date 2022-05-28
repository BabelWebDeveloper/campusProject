package pl.britenet.campus.obj.model.reportModel;

public class CategoryProductsReport {
    private final String name;
    private final int productsAmount;

    public CategoryProductsReport(String name, int productsAmount) {
        this.name = name;
        this.productsAmount = productsAmount;
    }

    public String getName() {
        return name;
    }

    public int getProductsAmount() {
        return productsAmount;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", ilość produktów: " + productsAmount;
    }
}
