package pl.britenet.campus.obj.reportCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.reportModel.CategoryProductsReport;
import pl.britenet.campus.obj.model.reportModel.OrderedByDiscountReport;
import pl.britenet.campus.service.reportService.CategoryProductService;
import pl.britenet.campus.service.reportService.OrderByDiscountService;

import java.util.List;

public class CreateOrderByDiscountReport extends Command {

    private final OrderByDiscountService orderByDiscountService;

    public CreateOrderByDiscountReport(OrderByDiscountService orderByDiscountService) {
        super("salesByDiscount");
        this.orderByDiscountService = orderByDiscountService;
    }

    @Override
    public void perform() {
        List<OrderedByDiscountReport> reports = orderByDiscountService.createOrderByDiscountReport();
        System.out.println("Sprzedane produkty wg promocji:");
        for (OrderedByDiscountReport report : reports) {
            System.out.println(report);
        }
    }
}
