package pl.britenet.campus.obj.cartCommands;

import pl.britenet.campus.builder.CartBuilder;
import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.Cart;
import pl.britenet.campus.service.CartService;

import java.util.Scanner;

public class CreateCartCommand extends Command {
    private final CartService cartService;

    public CreateCartCommand(CartService cartService) {
        super("create-cart");

        this.cartService = cartService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        int id = 0;

        System.out.println("Wprowadź customer_id:");
        int customer_id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź total_cost:");
        double total_cost = scanner.nextDouble();//przecinek zamienić na kropkę
        System.out.println(total_cost);
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

        cartService.create(cart);

        System.out.println(cart);
    }
}
