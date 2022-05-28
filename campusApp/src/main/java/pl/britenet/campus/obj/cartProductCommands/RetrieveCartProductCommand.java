package pl.britenet.campus.obj.cartProductCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.CartProduct;
import pl.britenet.campus.service.CartProductService;

import java.util.Scanner;

public class RetrieveCartProductCommand extends Command {
    private final CartProductService cartProductService;

    public RetrieveCartProductCommand(CartProductService cartProductService) {
        super("retrieve-cartProduct");

        this.cartProductService = cartProductService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID cardProduct");
        int id = scanner.nextInt();

        cartProductService.display(id);
        CartProduct cartProduct = cartProductService.retrieve(id).orElseThrow();
    }
}
