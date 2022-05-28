package pl.britenet.campus.obj.categoryCommands;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.service.CategoryService;
import pl.britenet.campus.service.ProductService;

import java.util.Scanner;

public class DeleteCategoryCommand extends Command {
    private final CategoryService categoryService;

    public DeleteCategoryCommand(CategoryService categoryService) {
        super("delete-category");

        this.categoryService = categoryService;
    }

    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID produktu, który chcesz usunąć:");
        int id = scanner.nextInt();

        categoryService.remove(id);

        System.out.println("Kategoria została usunięta.");
    }
}
