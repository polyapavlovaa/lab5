package commands;

import exceptions.NoSuchCommandException;
import lib.DataReader;
import lib.DataWriter;
import lib.StorageForCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static lib.ConsoleManager.PrintMsg;

// another unfinished launcher
public class Console {
    StorageForCommands comands;
    List<Commandable> commandsList;
    Scanner scanner;

    public Console(Scanner scanner, DataWriter writer, DataReader reader){
        this.scanner = scanner;
        comands = new StorageForCommands();
        commandsList = new ArrayList<>();
        commandsList = comands.getCommandsList(reader,writer,scanner);
    }

    public void run() {
        String command = null;
        PrintMsg("Hello! Please, enter help to get information about commands");

        while(scanner.hasNext()){
            command = scanner.nextLine().trim();
            try {
                executeCommand(command);
                return;
            } catch (NoSuchCommandException e) {
                e.getMessage();
            }
        }
    }


    private String getArgOfCommand(String inputCommand){
        String argument = " ";
        String[] command = inputCommand.trim().split("");
        if(command.length<2){
            PrintMsg("You entered a command with no argument");
        } else {
            PrintMsg("You entered a command with an argument: " + argument);
            argument = command[1];
        }
        return argument;
    }

    public void executeCommand(String inCommand) throws NoSuchCommandException {
        for (Commandable commandable : commandsList){
            if(commandable.getName() == inCommand){
                PrintMsg("You enter a right commandable!");
                commandable.execute(getArgOfCommand(inCommand));
            } else throw new NoSuchCommandException();
        }

    }

}
