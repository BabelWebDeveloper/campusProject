package pl.britenet.campus.obj.cartProductCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.service.CartProductService;

import java.util.Scanner;

public class DeleteCartProductCommand extends Command {
    private final CartProductService cartProductService;

    public DeleteCartProductCommand(CartProductService cartProductService) {
        super("delete-cartProduct");

        this.cartProductService = cartProductService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID cardProduct");
        int id = scanner.nextInt();
        scanner.nextLine();

        cartProductService.remove(id);

        System.out.println("Karta została usunięta.");
    }
}
