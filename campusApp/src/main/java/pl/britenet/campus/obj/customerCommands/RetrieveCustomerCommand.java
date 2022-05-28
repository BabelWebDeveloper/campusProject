package pl.britenet.campus.obj.customerCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.service.CustomerService;

import java.util.Scanner;

public class RetrieveCustomerCommand extends Command {
    private final CustomerService customerService;

    public RetrieveCustomerCommand(CustomerService customerService) {
        super("retrieve-customer");

        this.customerService = customerService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID klienta, którego chcesz wyświetlić.");
        int id = scanner.nextInt();

        customerService.display(id);
    }
}
