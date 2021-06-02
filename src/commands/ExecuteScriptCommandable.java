package commands;

import lib.*;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import static lib.Reader.PrintMsg;

// another (should be) unfinished script executor
public class ExecuteScriptCommandable extends Console
        implements Commandable {
    private FileManager fileManager;
    private StorageForCommands storage;
    private final ConsoleManager consoleManager;
    private final List<String> scripts;

    public ExecuteScriptCommandable(FileManager fileManager, Scanner scanner, ConsoleManager consoleManager) {
        super(scanner, fileManager);
        scripts = new Vector<>();
        this.consoleManager = consoleManager;
    }

    @Override
    public boolean execute(String argument) {
        ScriptManager scriptManager = new ScriptManager(argument.trim());
        scripts.add(argument.trim());
        while (scanner.hasNext()) {
            String nextLine = scriptManager.nextLine();
            if (nextLine == null)
                break;
            String[] cmd = (nextLine.trim() + " ").split(" ", 2);
            if (cmd[0].trim().equals("help")) {
                for (Commandable commandable : commandsList) {
                    PrintMsg(commandable.getName() + commandable.getDescription());
                }
            }
        }
        return true;
    }

    @Override
    public String getDescription() {
        return " execute line by line commands from the script\n";
    }
}
