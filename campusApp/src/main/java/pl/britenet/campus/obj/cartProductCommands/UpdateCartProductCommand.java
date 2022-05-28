package pl.britenet.campus.obj.cartProductCommands;

import pl.britenet.campus.builder.CartProductBuilder;
import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.CartProduct;
import pl.britenet.campus.service.CartProductService;

import java.util.Scanner;

public class UpdateCartProductCommand extends Command {
    private final CartProductService cartProductService;

    public UpdateCartProductCommand(CartProductService cartProductService) {
        super("update-cartProduct");

        this.cartProductService = cartProductService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID cardProduct");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź card_id:");
        int card_id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź product_id:");
        int product_id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź quantity:");
        int quantity = scanner.nextInt();

        CartProduct cartProduct = new CartProductBuilder(id)
                .setCardId(card_id)
                .setProductId(product_id)
                .setQuantity(quantity)
                .getCardProduct();

        cartProductService.update(cartProduct);

        cartProduct = cartProductService.retrieve(id).orElseThrow();

        System.out.println(cartProduct);
    }
}
