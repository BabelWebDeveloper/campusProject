package pl.britenet.campus.obj.categoryCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.service.CategoryService;
import pl.britenet.campus.service.ProductService;

import java.util.Scanner;

public class RetrieveCategoryCommand extends Command {
    private final CategoryService categoryService;

    public RetrieveCategoryCommand(CategoryService categoryService) {
        super("retrieve-category");

        this.categoryService = categoryService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID kategorii, którą chcesz wyświetlić.");
        int id = scanner.nextInt();

        categoryService.display(id);
    }
}
