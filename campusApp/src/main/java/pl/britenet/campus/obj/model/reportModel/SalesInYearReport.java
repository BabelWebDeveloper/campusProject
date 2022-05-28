package pl.britenet.campus.obj.model.reportModel;

public class SalesInYearReport {

    private final int year;
    private final int amount;

    public SalesInYearReport(int year, int amount) {
        this.year = year;
        this.amount = amount;
    }

    public int getYear() {
        return year;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Rok: " + year + " " +
                "Ilość sprzedanych produktów: " + amount;
    }
}
