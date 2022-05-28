package pl.britenet.campus.obj;

public abstract class Command {

    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract void perform();

    public String getName() {
        return name;
    }
}
