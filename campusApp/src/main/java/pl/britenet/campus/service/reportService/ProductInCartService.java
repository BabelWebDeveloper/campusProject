package pl.britenet.campus.service.reportService;

import pl.britenet.campus.obj.model.reportModel.ProductInCartReport;
import pl.britenet.campus.obj.model.reportModel.SalesInYearReport;
import pl.britenet.campus.service.database.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class ProductInCartService {

    private final DatabaseService databaseService;

    public ProductInCartService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<ProductInCartReport> createProductInCartReport() {
        String query = "SELECT ct.cartId AS cartId, p.name AS Nazwa, ct.quantity AS \"Ilość\" FROM cartproduct ct INNER JOIN product P ON ct.productId = p.id GROUP BY cartId";
        return this.databaseService.performQuery(query, res -> {

            List<ProductInCartReport> reports = new ArrayList<>();

            while (res.next()) {
                int year = res.getInt("cartId");
                String name = res.getString("Nazwa");
                int quantity = res.getInt("Ilość");

                reports.add(new ProductInCartReport(year, name, quantity));
            }

            return reports;
        });
    }
}
