package pl.britenet.campus.obj.model.reportModel;

public class SalesInMonthReport {

    private final int month;
    private final int amount;

    public SalesInMonthReport(int month, int amount) {
        this.month = month;
        this.amount = amount;
    }

    public int getMonth() {
        return month;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Miesiąc: " + month + " " +
                "Ilość sprzedanych produktów: " + amount;
    }
}
