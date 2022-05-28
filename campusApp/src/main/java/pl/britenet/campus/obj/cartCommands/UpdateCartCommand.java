package pl.britenet.campus.obj.cartCommands;

import pl.britenet.campus.builder.CartBuilder;
import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.Cart;
import pl.britenet.campus.service.CartService;

import java.util.Scanner;

public class UpdateCartCommand extends Command {
    private final CartService cartService;

    public UpdateCartCommand(CartService cartService) {
        super("update-cart");

        this.cartService = cartService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID karty:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź customer_id:");
        int customer_id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź total_cost:");
        double total_cost = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Wprowadź isOrdered: y/n");
        String isOrderedString = scanner.nextLine();
        boolean isOrdered;
        if (isOrderedString.equals("y")){
            isOrdered = true;
        } else if (isOrderedString.equals("n")){
            isOrdered = false;
        } else {
            System.out.println("Wrong command! isOrderd set as false");
            isOrdered = false;
        }

        Cart cart = new CartBuilder(id)
                .setCustomerId(customer_id)
                .setTotal_Cost(total_cost)
                .setOrdered(isOrdered)
                .getCard();

        cartService.update(cart);

        System.out.println(cart);
    }
}
