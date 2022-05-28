package pl.britenet.campus.obj;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("exit");
    }

    @Override
    public void perform() {
        System.out.println("App is shutting down...");
    }

}
