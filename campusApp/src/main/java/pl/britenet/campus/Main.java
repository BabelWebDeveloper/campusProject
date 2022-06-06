package pl.britenet.campus;

import pl.britenet.campus.obj.*;
import pl.britenet.campus.obj.cartCommands.CreateCartCommand;
import pl.britenet.campus.obj.cartCommands.DeleteCartCommand;
import pl.britenet.campus.obj.cartCommands.RetrieveCartCommand;
import pl.britenet.campus.obj.cartCommands.UpdateCartCommand;

import pl.britenet.campus.obj.cartProductCommands.CreateCartProductCommand;
import pl.britenet.campus.obj.cartProductCommands.DeleteCartProductCommand;
import pl.britenet.campus.obj.cartProductCommands.RetrieveCartProductCommand;
import pl.britenet.campus.obj.cartProductCommands.UpdateCartProductCommand;
import pl.britenet.campus.obj.categoryCommands.CreateCategoryCommand;
import pl.britenet.campus.obj.categoryCommands.DeleteCategoryCommand;
import pl.britenet.campus.obj.categoryCommands.RetrieveCategoryCommand;
import pl.britenet.campus.obj.categoryCommands.UpdateCategoryCommand;

import pl.britenet.campus.obj.customerCommands.CreateCustomerCommand;
import pl.britenet.campus.obj.customerCommands.DeleteCustomerCommand;
import pl.britenet.campus.obj.customerCommands.RetrieveCustomerCommand;
import pl.britenet.campus.obj.customerCommands.UpdateCustomerCommand;

import pl.britenet.campus.obj.discountCommands.CreateDiscountCommand;
import pl.britenet.campus.obj.discountCommands.DeleteDiscountCommand;
import pl.britenet.campus.obj.discountCommands.RetrieveDiscountCommand;
import pl.britenet.campus.obj.discountCommands.UpdateDiscountCommand;
import pl.britenet.campus.obj.paymentCommands.CreatePaymentCommand;
import pl.britenet.campus.obj.paymentCommands.DeletePaymentCommand;
import pl.britenet.campus.obj.paymentCommands.RetrievePaymentCommand;
import pl.britenet.campus.obj.paymentCommands.UpdatePaymentCommand;
import pl.britenet.campus.obj.productCommands.*;
import pl.britenet.campus.obj.reportCommands.*;
import pl.britenet.campus.service.*;
import pl.britenet.campus.service.database.DatabaseService;
import pl.britenet.campus.service.reportService.*;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        Locale.setDefault(Locale.US);
        DatabaseService databaseService = new DatabaseService();
        ProductService productService = new ProductService(databaseService);
        CategoryService categoryService = new CategoryService(databaseService);
        CartService cartService = new CartService(databaseService/*, cartProductService*/);
        CartProductService cartProductService = new CartProductService(databaseService);
        CustomerService customerService = new CustomerService(databaseService);
        PaymentService paymentService = new PaymentService(databaseService);
        DiscountService discountService = new DiscountService(databaseService);

//        Raporty:
        CategoryProductService categoryProductService = new CategoryProductService(databaseService);
        OrderByDiscountService orderByDiscountService = new OrderByDiscountService(databaseService);
        SalesInYearService salesInYearService = new SalesInYearService(databaseService);
        SalesInMonthService salesInMonthService = new SalesInMonthService(databaseService);
        ProductCustomerService productCustomerService = new ProductCustomerService(databaseService);
        ProductInCartService productInCartService = new ProductInCartService(databaseService);

        CommandService commandService = new CommandService();

        HelpCommand helpCommand = new HelpCommand();
        ExitCommand exitCommand = new ExitCommand();
//comment

        commandService.registerCommand(helpCommand);
        commandService.registerCommand(exitCommand);

        commandService.registerCommand(new CreateProductCommand(productService));
        commandService.registerCommand(new RetrieveProductCommand(productService));
        commandService.registerCommand(new UpdateProductCommand(productService));
        commandService.registerCommand(new DeleteProductCommand(productService));
        commandService.registerCommand(new RetrieveProductByNameCommand(productService));

        commandService.registerCommand(new CreateCategoryCommand(categoryService));
        commandService.registerCommand(new RetrieveCategoryCommand(categoryService));
        commandService.registerCommand(new UpdateCategoryCommand(categoryService));
        commandService.registerCommand(new DeleteCategoryCommand(categoryService));

        commandService.registerCommand(new CreateCartCommand(cartService));
        commandService.registerCommand(new RetrieveCartCommand(cartService));
        commandService.registerCommand(new UpdateCartCommand(cartService));
        commandService.registerCommand(new DeleteCartCommand(cartService));

        commandService.registerCommand(new CreateCartProductCommand(cartProductService));
        commandService.registerCommand(new RetrieveCartProductCommand(cartProductService));
        commandService.registerCommand(new UpdateCartProductCommand(cartProductService));
        commandService.registerCommand(new DeleteCartProductCommand(cartProductService));

        commandService.registerCommand(new CreateCustomerCommand(customerService));
        commandService.registerCommand(new RetrieveCustomerCommand(customerService));
        commandService.registerCommand(new UpdateCustomerCommand(customerService));
        commandService.registerCommand(new DeleteCustomerCommand(customerService));

        commandService.registerCommand(new CreatePaymentCommand(paymentService));
        commandService.registerCommand(new RetrievePaymentCommand(paymentService));
        commandService.registerCommand(new UpdatePaymentCommand(paymentService));
        commandService.registerCommand(new DeletePaymentCommand(paymentService));

        commandService.registerCommand(new CreateDiscountCommand(discountService));
        commandService.registerCommand(new CreateDiscountCommand(discountService));
        commandService.registerCommand(new RetrieveDiscountCommand(discountService));
        commandService.registerCommand(new UpdateDiscountCommand(discountService));
        commandService.registerCommand(new DeleteDiscountCommand(discountService));

//        Komendy raportów :
        commandService.registerCommand(new CreateCategoryProductReport(categoryProductService));
        commandService.registerCommand(new CreateOrderByDiscountReport(orderByDiscountService));
        commandService.registerCommand(new CreateSalesInYearReport(salesInYearService));
        commandService.registerCommand(new CreateSalesInMonthReport(salesInMonthService));
        commandService.registerCommand(new CreateProductCustomerReport(productCustomerService));
        commandService.registerCommand(new CreateProductInCartReport(productInCartService));

        commandService.getCommand("help").get().perform();

        boolean isRunning = true;

        while (isRunning){
            String userCommand = scanner.nextLine();
            Optional<Command> oCommand = commandService.getCommand(userCommand);

            if (userCommand.equals("exit")){
                isRunning = false;
            }

            if (oCommand.isPresent()) {
                oCommand.get().perform();
            }
            else {
                System.out.println("Nieznana komenda.");
            }
        }
//        System.out.println(cartService.create("1", String.valueOf(34)));
//        System.out.println(cartService.retrieveMaxCartId());
//        System.out.println(cartService.retrieveProductsInCart(3));
//        System.out.println(customerService.retrieve("@przemo","123"));
//        cartService.update(1);
//        cartProductService.cartProductDecrement(25);
//        System.out.println(customerService.create("1","1", "1","1", "1"));
    }

}
