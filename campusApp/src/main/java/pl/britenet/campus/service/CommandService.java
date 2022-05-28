package pl.britenet.campus.service;

import pl.britenet.campus.obj.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandService {

    private final List<Command> commands;

    public CommandService() {
        this.commands = new ArrayList<>();
    }

    public void registerCommand(Command command) {
        this.commands.add(command);
    }

    public Optional<Command> getCommand(String name) {
        return this.commands.stream()
                .filter( command -> command.getName().equalsIgnoreCase(name) )
                .findFirst();
    }

}
