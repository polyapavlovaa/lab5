package commands;

import exceptions.EmptyIOException;
import exceptions.NoSuchIdException;
import lib.CollectionManager;
import lib.ConsoleManager;

import static lib.ConsoleManager.PrintMsg;

public class Remove implements Commandable {
    private final CollectionManager collectionManager;
    private final ConsoleManager consoleManager;

    public Remove(CollectionManager collectionManager, ConsoleManager consoleManager) {
        this.collectionManager = collectionManager;
        this.consoleManager = consoleManager;
    }

    /**
     * execute remove command
     * @param argument id
     */

    @Override
    public boolean execute(String argument) {
        int id;
        try {
            if (argument.trim().isEmpty()) throw new EmptyIOException();
            id = consoleManager.readInteger("Enter an id");
            if (!collectionManager.isEqualId(id)) throw new NoSuchIdException();
        } catch (EmptyIOException e){
            PrintMsg("You entered a null-argument");
            return true;
        }catch (NoSuchIdException e){
            PrintMsg("No element with such id in collection");
            return true;
        }
        collectionManager.removeID(id);
        PrintMsg("removed\n");
        return true;
    }

    @Override
    public String getDescription() {
        return " (id) remove an item from the collection by its id\n";
    }
}
