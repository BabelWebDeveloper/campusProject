package pl.britenet.campus.obj.customerCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.service.CustomerService;

import java.util.Scanner;

public class DeleteCustomerCommand extends Command {
    private final CustomerService customerService;

    public DeleteCustomerCommand(CustomerService customerService) {
        super("delete-customer");

        this.customerService = customerService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID klienta, którego chcesz usunąć:");
        int id = scanner.nextInt();

        customerService.remove(id);

        System.out.println("Klient został usunięta.");
    }
}
