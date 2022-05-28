package pl.britenet.campus.service.reportService;

import pl.britenet.campus.obj.model.reportModel.CategoryProductsReport;
import pl.britenet.campus.obj.model.reportModel.ProductCustomerReport;
import pl.britenet.campus.service.database.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class ProductCustomerService {
    private final DatabaseService databaseService;

    public ProductCustomerService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<ProductCustomerReport> createProductCustomerReport() {
        String query = "SELECT p.name AS \"Nazwa\", COUNT(ct.customerId) AS \"Ilość\" FROM cart ct INNER JOIN customer c ON ct.customerId = c.id INNER JOIN cartproduct cp ON cp.cartId = ct.id INNER JOIN product p ON cp.productId = p.id WHERE ct.isOrdered = 1 GROUP BY cp.productId";
        return this.databaseService.performQuery(query, res -> {

            List<ProductCustomerReport> reports = new ArrayList<>();

            while (res.next()) {
                String name = res.getString("Nazwa");
                int productsAmount = res.getInt("Ilość");

                reports.add(new ProductCustomerReport(name, productsAmount));
            }

            return reports;

        });
    }
}
