package pl.britenet.campus.obj.model.reportModel;

public class OrderedByDiscountReport {

    private final String description;
    private final int productsAmount;

    public OrderedByDiscountReport(String description, int productsAmount) {
        this.description = description;
        this.productsAmount = productsAmount;
    }

    public String getDescription() {
        return description;
    }

    public int getProductsAmount() {
        return productsAmount;
    }

    @Override
    public String toString() {
        return "Opis promki: " + description + " " +
                "Ilość sprzedanych produktów: " + productsAmount;
    }
}
