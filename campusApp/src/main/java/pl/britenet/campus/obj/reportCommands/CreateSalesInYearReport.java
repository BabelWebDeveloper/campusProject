package pl.britenet.campus.obj.reportCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.reportModel.SalesInMonthReport;
import pl.britenet.campus.obj.model.reportModel.SalesInYearReport;
import pl.britenet.campus.service.reportService.SalesInYearService;

import java.util.List;

public class CreateSalesInYearReport extends Command {

    private final SalesInYearService salesInYearService;

    public CreateSalesInYearReport(SalesInYearService salesInYearService) {
        super("salesYear");
        this.salesInYearService = salesInYearService;
    }

    @Override
    public void perform() {
        List<SalesInYearReport> reports = salesInYearService.createSalesInYearReport();
        System.out.println("Sprzedaż wg miesięcy:");
        for (SalesInYearReport report : reports) {
            System.out.println(report);
        }
    }
}
