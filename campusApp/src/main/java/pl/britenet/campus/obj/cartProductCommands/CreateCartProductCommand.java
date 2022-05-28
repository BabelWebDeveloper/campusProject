package pl.britenet.campus.obj.cartProductCommands;

import pl.britenet.campus.builder.CartProductBuilder;
import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.CartProduct;
import pl.britenet.campus.service.CartProductService;

import java.util.Scanner;

public class CreateCartProductCommand extends Command {
    private final CartProductService cartProductService;

    public CreateCartProductCommand(CartProductService cartProductService) {
        super("create-cartProduct");

        this.cartProductService = cartProductService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        int id = 0;

        System.out.println("Wprowadź cartId:");
        int card_id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź productId:");
        int product_id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź quantity:");
        int quantity = scanner.nextInt();

        CartProduct cartProduct = new CartProductBuilder(id)
                .setCardId(card_id)
                .setProductId(product_id)
                .setQuantity(quantity)
                .getCardProduct();

        cartProductService.create(cartProduct);

        System.out.println(cartProduct);
    }
}
