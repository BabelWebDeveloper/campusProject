package pl.britenet.campus.obj.productCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.service.ProductService;

import java.util.Scanner;

public class RetrieveProductByNameCommand extends Command {
    private final ProductService productService;

    public RetrieveProductByNameCommand(ProductService productService) {
        super("retrieve-product-name");

        this.productService = productService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź nazwę produktu, który chcesz wyświetlić.");
        String name = scanner.nextLine();

        try {
            productService.retrieve(name);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }
}
