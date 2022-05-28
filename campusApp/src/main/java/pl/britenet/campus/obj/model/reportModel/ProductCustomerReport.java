package pl.britenet.campus.obj.model.reportModel;

public class ProductCustomerReport {

    private final String name;
    private final int cAmount;

    public ProductCustomerReport(String name, int cAmount) {
        this.name = name;
        this.cAmount = cAmount;
    }

    public String getName() {
        return name;
    }

    public int getCamount() {
        return cAmount;
    }

    @Override
    public String toString() {
        return "Nazwa produktu: " + name + " " +
                "Ilość klientów: " + cAmount;
    }
}
