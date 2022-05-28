package pl.britenet.campus.obj.productCommands;

import pl.britenet.campus.builder.ProductBuilder;
import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.service.ProductService;

import java.util.Scanner;

public class UpdateProductCommand extends Command {
    private final ProductService productService;

    public UpdateProductCommand(ProductService productService) {
        super("update-product");

        this.productService = productService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID produktu, który chcesz zaktualizować:");
        int id = scanner.nextInt();

        if (productService.retrieve(id).isPresent()){
            scanner.nextLine();
        } else {
            System.out.println("Nie ma takiego produktu.");
        }

        System.out.println("Wprowadź nazwę produktu:");
        String name = scanner.nextLine();

        System.out.println("Wprowadź opis produktu:");
        String description = scanner.nextLine();

        System.out.println("Wprowadź cenę produktu:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Wprowadź id kategorii:");
        int category_id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Wprowadź poziom zniżki:");
        int discount_id = scanner.nextInt();

        Product product = new ProductBuilder(id)
                .setName(name)
                .setDescription(description)
                .setPrice(price)
                .setCategoryId(category_id)
                .setDiscount(discount_id)
                .getProduct();

        productService.update(product);

        System.out.println("Produkt został zaktualizowany.");
        System.out.println(product);
    }
}
