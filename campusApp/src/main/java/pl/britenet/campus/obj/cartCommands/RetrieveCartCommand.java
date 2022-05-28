package pl.britenet.campus.obj.cartCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.service.CartService;

import java.util.Scanner;

public class RetrieveCartCommand extends Command {
    private final CartService cartService;

    public RetrieveCartCommand(CartService cartService) {
        super("retrieve-cart");

        this.cartService = cartService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID karty, którą chcesz wyświetlić.");
        int id = scanner.nextInt();

        cartService.display(id);
    }
}
