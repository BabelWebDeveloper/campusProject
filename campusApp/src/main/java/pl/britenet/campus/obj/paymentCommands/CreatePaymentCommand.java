package pl.britenet.campus.obj.paymentCommands;

import pl.britenet.campus.builder.PaymentBuilder;
import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.Payment;
import pl.britenet.campus.service.PaymentService;

import java.util.Scanner;

public class CreatePaymentCommand extends Command {
    private final PaymentService paymentService;

    public CreatePaymentCommand(PaymentService paymentService) {
        super("create-payment");

        this.paymentService = paymentService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        int id = 0;

        System.out.println("Wprowadź CardID:");
        int cardId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź date");
        String date = scanner.nextLine();

        Payment payment = new PaymentBuilder(id)
                .setCartId(cardId)
                .setDate(date)
                .getPayment();

        paymentService.create(payment);

        System.out.println(payment);
    }
}
