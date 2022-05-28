package pl.britenet.campus.obj.productCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.service.ProductService;

import java.util.Scanner;

public class DeleteProductCommand extends Command {
    private final ProductService productService;

    public DeleteProductCommand(ProductService productService) {
        super("delete-product");

        this.productService = productService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID produktu, który chcesz usunąć:");
        int id = scanner.nextInt();

        productService.remove(id);
        System.out.println("Produkt został usunięty.");
    }
}
