package pl.britenet.campus.obj.cartCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.service.CartService;

import java.util.Scanner;

public class DeleteCartCommand extends Command {
    private final CartService cartService;

    public DeleteCartCommand(CartService cartService) {
        super("delete-cart");

        this.cartService = cartService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID pkarty, którą chcesz usunąć:");
        int id = scanner.nextInt();

        cartService.remove(id);

        System.out.println("Karta została usunięta.");
    }
}
