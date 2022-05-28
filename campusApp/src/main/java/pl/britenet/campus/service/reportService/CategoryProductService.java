package pl.britenet.campus.service.reportService;

import pl.britenet.campus.obj.model.reportModel.CategoryProductsReport;
import pl.britenet.campus.service.database.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class CategoryProductService {
    private final DatabaseService databaseService;

    public CategoryProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<CategoryProductsReport> createCategoryProductsReport() {
        String query = "SELECT c.name, COUNT(p.categoryId) AS productsAmount FROM product p RIGHT JOIN category c ON p.categoryId = c.id GROUP BY p.categoryId";
        return this.databaseService.performQuery(query, res -> {

            List<CategoryProductsReport> reports = new ArrayList<>();

            while (res.next()) {
                String name = res.getString("name");
                int productsAmount = res.getInt("productsAmount");

                reports.add(new CategoryProductsReport(name, productsAmount));
            }

            return reports;

        });
    }
}
