package commands;

import java.util.List;

import static lib.Reader.PrintMsg;

public class Help implements Commandable {
    private final List<Commandable> commandsAvailable;


    public Help(List<Commandable> commandsAvailable){
        this.commandsAvailable = commandsAvailable;
        commandsAvailable.add(0,this);
    }

    /**
     * execute help command
     */

    @Override
    public boolean execute(String argument) {
        StringBuilder res = new StringBuilder();
        PrintMsg("\nexecute_script - read and execute the script from the specified file");
        for (Commandable commandable : commandsAvailable){
            res.append(commandable.getName()).append(" - ").append(commandable.getDescription()).append("\n");
        }
        PrintMsg(res.toString()+"\ncommand help executed\n");
        return true;
    }

    @Override
    public String getDescription() {
        return "display help for available commands";
    }

    public List<Commandable> getCommandsAvailable() {
        return commandsAvailable;
    }
}
