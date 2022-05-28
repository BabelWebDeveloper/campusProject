package pl.britenet.campus.obj.productCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.service.ProductService;

import java.util.Scanner;

public class RetrieveProductCommand extends Command {
    private final ProductService productService;

    public RetrieveProductCommand(ProductService productService) {
        super("retrieve-product");

        this.productService = productService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID produktu, który chcesz wyświetlić.");
        int id = scanner.nextInt();

//        try {
            productService.display(id);
//        }
//        catch (NullPointerException e) {
//            System.out.println(e.getMessage());
//        }

    }
}
