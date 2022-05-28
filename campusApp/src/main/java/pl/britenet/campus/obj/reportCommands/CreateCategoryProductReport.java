package pl.britenet.campus.obj.reportCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.reportModel.CategoryProductsReport;
import pl.britenet.campus.obj.model.reportModel.ProductInCartReport;
import pl.britenet.campus.service.reportService.CategoryProductService;

import java.util.List;

public class CreateCategoryProductReport extends Command {

    private final CategoryProductService categoryProductService;

    public CreateCategoryProductReport(CategoryProductService categoryProductService) {
        super("salesByCategory");
        this.categoryProductService = categoryProductService;
    }

    @Override
    public void perform() {
        List<CategoryProductsReport> reports = categoryProductService.createCategoryProductsReport();
        System.out.println("Produkty wg kategorii:");
        for (CategoryProductsReport report : reports) {
            System.out.println(report);
        }
    }
}
