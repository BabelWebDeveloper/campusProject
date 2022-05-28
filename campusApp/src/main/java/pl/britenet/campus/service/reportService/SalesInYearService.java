package pl.britenet.campus.service.reportService;

import pl.britenet.campus.obj.model.reportModel.SalesInYearReport;
import pl.britenet.campus.service.database.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class SalesInYearService {

    private final DatabaseService databaseService;

    public SalesInYearService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<SalesInYearReport> createSalesInYearReport() {
        String query = "SELECT CAST((SUBSTRING(date,-2,8)) AS INT) AS Year, COUNT(ct.quantity) AS \"Ilość\" FROM payment p INNER JOIN cart c ON p.cartId = c.id INNER JOIN cartproduct ct ON ct.cartId = c.id WHERE c.isOrdered GROUP BY Year";
        return this.databaseService.performQuery(query, res -> {

            List<SalesInYearReport> reports = new ArrayList<>();

            while (res.next()) {
                int year = res.getInt("Year");
                int productsAmount = res.getInt("Ilość");

                reports.add(new SalesInYearReport(year, productsAmount));
            }

            return reports;
        });
    }
}
