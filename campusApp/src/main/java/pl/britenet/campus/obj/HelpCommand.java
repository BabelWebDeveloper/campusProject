package pl.britenet.campus.obj;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public void perform() {
        System.out.println("====================");
        System.out.println("Lista dostępnych komend:\n" +
                "help -> Menu\n" +
                "\n" +
                "create- -> Stwórz nowy\n" +
                "retrieve- -> Wybierz i wyświetl \n" +
                "retrieve-product-name -> produkt po nazwie\n" +
                "update- -> Zaktualizuj\n" +
                "delete- -> Usuń\n" +
                "=======================\n" +
                "Raporty:\n" +
                "salesByCategory\n" +
                "salesByDiscount\n" +
                "salesYear\n" +
                "salesMonth\n" +
                "salesByProduct\n" +
                "salesCart");
        System.out.println("====================");
        System.out.println("exit -> exit from app");
    }

}
