package pl.britenet.campus.obj.paymentCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.Payment;
import pl.britenet.campus.service.PaymentService;

import java.util.Scanner;

public class DeletePaymentCommand extends Command {
    private final PaymentService paymentService;

    public DeletePaymentCommand(PaymentService paymentService) {
        super("delete-payment");

        this.paymentService = paymentService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID płatności, który chcesz usunąc:");
        int id = scanner.nextInt();

        paymentService.remove(id);
        System.out.println("Płatność została usunięta.");
    }
}
