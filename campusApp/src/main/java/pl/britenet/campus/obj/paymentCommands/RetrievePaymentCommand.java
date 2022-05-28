package pl.britenet.campus.obj.paymentCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.Payment;
import pl.britenet.campus.service.PaymentService;

import java.util.Scanner;

public class RetrievePaymentCommand extends Command {
    private final PaymentService paymentService;

    public RetrievePaymentCommand(PaymentService paymentService) {
        super("retrieve-payment");

        this.paymentService = paymentService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID płatności, który chcesz zobzyc:");
        int id = scanner.nextInt();

        paymentService.display(id);
    }
}
