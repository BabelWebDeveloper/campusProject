package pl.britenet.campus.obj.discountCommands;

import pl.britenet.campus.builder.DiscountBuilder;
import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.Discount;
import pl.britenet.campus.service.DiscountService;

import java.util.Scanner;

public class UpdateDiscountCommand extends Command {
    private final DiscountService discountService;

    public UpdateDiscountCommand(DiscountService discountService) {
        super("update-discount");

        this.discountService = discountService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID promocji, którą chcesz zaktualizować:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź discount_percent:");
        int discount_percent = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź opis");
        String discount_description = scanner.nextLine();

        Discount discount = new DiscountBuilder(id)
                .setDiscountPercent(discount_percent)
                .setDescription(discount_description)
                .getDiscount();

        discountService.update(discount);

        System.out.println("Promocja została zaktualizowana.");
        System.out.println(discount);
    }
}
