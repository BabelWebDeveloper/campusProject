package pl.britenet.campus.obj.discountCommands;

import pl.britenet.campus.builder.DiscountBuilder;
import pl.britenet.campus.builder.ProductBuilder;
import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.Discount;
import pl.britenet.campus.obj.model.Payment;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.service.DiscountService;
import pl.britenet.campus.service.PaymentService;

import java.util.Scanner;

public class CreateDiscountCommand extends Command {
    private final DiscountService discountService;

    public CreateDiscountCommand(DiscountService discountService) {
        super("create-discount");

        this.discountService = discountService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        int id = 0;

        System.out.println("Wprowadź discount_percent:");
        int discount_percent = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź opis");
        String discount_description = scanner.nextLine();

        Discount discount = new DiscountBuilder(id)
                .setDiscountPercent(discount_percent)
                .setDescription(discount_description)
                .getDiscount();

        discountService.create(discount);

//        discount = discountService.retrieve(id).orElseThrow();

        System.out.println(discount);
    }
}
