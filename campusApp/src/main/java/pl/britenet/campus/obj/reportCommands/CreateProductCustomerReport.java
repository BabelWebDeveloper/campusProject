package pl.britenet.campus.obj.reportCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.reportModel.OrderedByDiscountReport;
import pl.britenet.campus.obj.model.reportModel.ProductCustomerReport;
import pl.britenet.campus.obj.model.reportModel.SalesInYearReport;
import pl.britenet.campus.service.reportService.ProductCustomerService;
import pl.britenet.campus.service.reportService.SalesInYearService;

import java.util.List;

public class CreateProductCustomerReport extends Command {

    private final ProductCustomerService productCustomerService;

    public CreateProductCustomerReport(ProductCustomerService productCustomerService) {
        super("salesByProduct");
        this.productCustomerService = productCustomerService;
    }

    @Override
    public void perform() {
        List<ProductCustomerReport> reports = productCustomerService.createProductCustomerReport();
        System.out.println("Ilość zamowień wg producktu:");
        for (ProductCustomerReport report : reports) {
            System.out.println(report);
        }
    }
}
