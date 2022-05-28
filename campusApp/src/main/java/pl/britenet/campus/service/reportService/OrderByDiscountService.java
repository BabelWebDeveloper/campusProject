package pl.britenet.campus.service.reportService;

import pl.britenet.campus.obj.model.reportModel.OrderedByDiscountReport;
import pl.britenet.campus.service.database.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class OrderByDiscountService {

    private final DatabaseService databaseService;

    public OrderByDiscountService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<OrderedByDiscountReport> createOrderByDiscountReport() {
        String query = "SELECT d.description AS \"Opis\", COUNT(cp.quantity) AS \"Ilość\" FROM discount d INNER JOIN product p ON p.discountId = d.id INNER JOIN cartproduct cp ON cp.productId = p.id INNER JOIN cart cr ON cp.cartId = cr.id WHERE cr.isOrdered = 1 GROUP BY d.id ORDER BY Ilość DESC";
        return this.databaseService.performQuery(query, res -> {

            List<OrderedByDiscountReport> reports = new ArrayList<>();

            while (res.next()) {
                String description = res.getString("Opis");
                int productsAmount = res.getInt("Ilość");

                reports.add(new OrderedByDiscountReport(description, productsAmount));
            }

            return reports;
        });
    }
}
