package pl.britenet.campus.obj.paymentCommands;

import pl.britenet.campus.builder.PaymentBuilder;
import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.Payment;
import pl.britenet.campus.service.PaymentService;

import java.util.Scanner;
public class UpdatePaymentCommand extends Command {
    private final PaymentService paymentService;
    public UpdatePaymentCommand(PaymentService paymentService) {
        super("update-payment");

        this.paymentService = paymentService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID płatności, który chcesz zaktualizowac:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź cardId:");
        int cardId = scanner.nextInt();
        scanner.nextLine();

        Payment payment = new PaymentBuilder(id)
                .setCartId(cardId)
                .setDate()
                .getPayment();

        paymentService.update(payment);

        System.out.println(payment);
    }
}
