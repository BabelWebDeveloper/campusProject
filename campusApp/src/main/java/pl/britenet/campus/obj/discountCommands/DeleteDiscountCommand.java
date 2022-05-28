package pl.britenet.campus.obj.discountCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.Discount;
import pl.britenet.campus.service.DiscountService;

import java.util.Scanner;

public class DeleteDiscountCommand extends Command {
    private final DiscountService discountService;

    public DeleteDiscountCommand(DiscountService discountService) {
        super("delete-discount");

        this.discountService = discountService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID promocji, którą chcesz usunąć:");
        int id = scanner.nextInt();
        scanner.nextLine();

        discountService.remove(id);

        System.out.println("Promocja została usunięta.");
    }
}
