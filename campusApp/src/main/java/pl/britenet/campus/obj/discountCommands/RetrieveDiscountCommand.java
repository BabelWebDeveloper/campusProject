package pl.britenet.campus.obj.discountCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.Discount;
import pl.britenet.campus.service.DiscountService;

import java.util.Scanner;

public class RetrieveDiscountCommand extends Command {
    private final DiscountService discountService;

    public RetrieveDiscountCommand(DiscountService discountService) {
        super("retrieve-discount");

        this.discountService = discountService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID promocji, którą chcesz zobzczyć:");
        int id = scanner.nextInt();
        scanner.nextLine();

        discountService.display(id);
    }
}
