package commands;

import exceptions.NoSuchCommandException;
import lib.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static lib.ConsoleManager.PrintErr;
import static lib.ConsoleManager.PrintMsg;

public class Executor {
    private Scanner scanner;
    private final List<Commandable> commandsList;
    private final List<String> script;


    public Executor(Scanner scanner, FileManager fileManager) {
        this.scanner = scanner;
        script = new ArrayList<>();
        StorageForCommands commands = new StorageForCommands();
        commandsList = commands.getCommandsList(fileManager, scanner);
    }

    /**
     * execute script file with commands
     * @param file script name
     * @throws NoSuchCommandException if no any available command to execute
     */

    public void executeScript(String file) throws NoSuchCommandException {
        ScriptManager scriptManager = new ScriptManager(file.trim());
        script.add(file.trim());
        boolean isRunning = true;
        while (isRunning) {
            String nextLine = scriptManager.nextLine();
            if (nextLine == null)
                break;
            String[] cmd = (nextLine.trim() + " ").split(" ", 2);
            if (cmd[0].trim().equals("execute_script")) {
                if (!cmd[1].equals(" ")) {
                    if (script.contains(cmd[1].trim())) {
                        PrintErr("trying to recursively call the script ");
                        break;
                    } else {
                        executeScript(cmd[1].trim());
                    }
                } else {
                    PrintErr("enter file name");
                }
            } else if (!cmd[0].trim().equals("")) {
                boolean isFindCommand = false;
                for (Commandable commandable : commandsList) {
                    if (cmd[0].trim().equals(commandable.getName())) {
                        if (commandable.getName().trim().equals("add")) {
                            ChangeScanner(scriptManager.getScriptReader());
                            try {
                                isRunning = commandable.execute(cmd[1]);
                                isFindCommand = true;
                            } catch (NoSuchElementException e) {
                                PrintErr("error reading element from file");
                            }
                            ChangeScanner(new Scanner(System.in));
                        } else {
                            commandable.execute(cmd[1]);
                            isFindCommand = true;
                            break;
                        }
                    }
                }
                if (!isFindCommand) {
                    throw new NoSuchCommandException();
                }
            }
        }
        PrintMsg("script executed\n");
    }

    /**
     * go to interactive console mode
     * @throws NoSuchCommandException if command is not available to execution
     */

    public void run() throws NoSuchCommandException {
        boolean isRunning = true;
        PrintMsg("Hello! Please, enter help to get info about available commands!");
        while (isRunning) {
            String[] cmd = (scanner.nextLine().trim() + " ").split(" ", 2);
            if (cmd[0].trim().equals("execute_script")) {
                if (!cmd[1].trim().equals("")) {
                    Path scriptPath = Paths.get(cmd[1].trim());
                    if (Files.isExecutable(scriptPath)) {
                        executeScript(cmd[1].trim());
                    }
                    script.clear();
                } else {
                    PrintErr("enter file name");
                }
            } else if (!cmd[0].trim().equals("")) {
                boolean isFindCommand = false;
                for (Commandable eachCommandable : commandsList) {
                    if (cmd[0].trim().equals(eachCommandable.getName())) {
                        isRunning = eachCommandable.execute(cmd[1]);
                        isFindCommand = true;
                        break;
                    }
                }
                if (!isFindCommand) {
                    PrintErr("No such command");
                }
            }
        }
    }

    public void ChangeScanner(Scanner sc) {
        scanner = sc;
    }
}
