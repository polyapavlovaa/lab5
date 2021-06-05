package commands;

import exceptions.EmptyIOException;
import lib.CollectionManager;

import static lib.ConsoleManager.PrintErr;
import static lib.ConsoleManager.PrintMsg;

public class FilterContainsName implements Commandable {
    private CollectionManager collectionManager;

    public FilterContainsName(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * execute filter_contains_name command
     * @param argument name to check
     */

    @Override
    public boolean execute(String argument) {
        if (argument.isEmpty()) {
            try {
                throw new EmptyIOException();
            } catch (EmptyIOException e) {
               PrintErr("You enter a null-argument");
            }
        }
        String list = collectionManager.containsSomeSubstring(argument);
        if (list.isEmpty()) {
            PrintMsg("No such element :( ");
        }
        PrintMsg(list + "elements are displayed\n");
        return true;
    }

    @Override
    public String getDescription() {
        return " (name) display elements whose name field value contains the given substring\n";
    }
}
