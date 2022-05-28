package pl.britenet.campus.obj.reportCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.reportModel.ProductCustomerReport;
import pl.britenet.campus.obj.model.reportModel.SalesInMonthReport;
import pl.britenet.campus.service.reportService.SalesInMonthService;

import java.util.List;

public class CreateSalesInMonthReport extends Command {

    private final SalesInMonthService salesInMonthService;

    public CreateSalesInMonthReport(SalesInMonthService salesInMonthService) {
        super("salesMonth");
        this.salesInMonthService = salesInMonthService;
    }

    @Override
    public void perform() {
        List<SalesInMonthReport> reports = salesInMonthService.createSalesInMonthReport();
        System.out.println("Sprzedaż wg miesięcy:");
        for (SalesInMonthReport report : reports) {
            System.out.println(report);
        }
    }
}
