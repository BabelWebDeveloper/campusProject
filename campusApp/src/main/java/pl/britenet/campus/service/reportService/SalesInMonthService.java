package pl.britenet.campus.service.reportService;

import pl.britenet.campus.obj.model.reportModel.SalesInMonthReport;
import pl.britenet.campus.obj.model.reportModel.SalesInYearReport;
import pl.britenet.campus.service.database.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class SalesInMonthService {

    private final DatabaseService databaseService;

    public SalesInMonthService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<SalesInMonthReport> createSalesInMonthReport() {
        String query = "SELECT CAST((SUBSTRING(date,-5,2)) AS INT) AS Month,COUNT(ct.quantity) AS \"Ilość\"FROM payment p INNER JOIN cart c ON p.cartId = c.id INNER JOIN cartproduct ct ON ct.cartId = c.id WHERE c.isOrdered GROUP BY Month";
        return this.databaseService.performQuery(query, res -> {

            List<SalesInMonthReport> reports = new ArrayList<>();

            while (res.next()) {
                int year = res.getInt("Month");
                int productsAmount = res.getInt("Ilość");

                reports.add(new SalesInMonthReport(year, productsAmount));
            }

            return reports;
        });
    }
}
