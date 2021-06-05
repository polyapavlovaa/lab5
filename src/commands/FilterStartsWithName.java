package commands;

import exceptions.EmptyIOException;
import lib.CollectionManager;

import static lib.ConsoleManager.PrintErr;
import static lib.ConsoleManager.PrintMsg;

public class FilterStartsWithName implements Commandable {
    private CollectionManager collectionManager;

    public FilterStartsWithName(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     * execute filter_starts_with_name command
     * @param argument name to check
     */

    @Override
    public boolean execute(String argument) {
        if (argument.isEmpty()) {
            try {
                throw new EmptyIOException();
            } catch (EmptyIOException e) {
                PrintErr("You enter null-argument");
                return true;
            }
        }
        String list = collectionManager.startsWithSubstring(argument);
        if (list.isEmpty()) {
            PrintMsg("No such element :( ");
        }
        PrintMsg(list+" elements are displayed");
        return true;
    }

    @Override
    public String getDescription() {
        return " (name) display elements whose name field value begins with a given substring\n";
    }
}
