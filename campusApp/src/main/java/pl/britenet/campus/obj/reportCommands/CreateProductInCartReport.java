package pl.britenet.campus.obj.reportCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.reportModel.ProductInCartReport;
import pl.britenet.campus.service.reportService.ProductInCartService;

import java.util.List;

public class CreateProductInCartReport extends Command {

    private final ProductInCartService productCustomerService;

    public CreateProductInCartReport(ProductInCartService productInCartService) {
        super("salesCart");
        this.productCustomerService = productInCartService;
    }

    @Override
    public void perform() {
        List<ProductInCartReport> reports = productCustomerService.createProductInCartReport();
        System.out.println("Produkty w poszczególnych koszykach:");
        for (ProductInCartReport report : reports) {
            System.out.println(report);
        }
    }
}
