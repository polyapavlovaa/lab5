package commands;

import models.Ticket;
import lib.CollectionManager;
import lib.ConsoleManager;

import static lib.Reader.PrintMsg;

public class RemoveLower implements Commandable {
    private final CollectionManager collectionManager;
    private final ConsoleManager consoleManager;

    public RemoveLower(CollectionManager collectionManager, ConsoleManager consoleManager){
        this.collectionManager = collectionManager;
        this.consoleManager = consoleManager;
    }

    /**
     * remove lower command
     * @param argument id
     */

    @Override
    public boolean execute(String argument) {
        Integer id;
        id = consoleManager.readInteger("Enter an id");
        Ticket object = consoleManager.getTicketObj(id);
        if (collectionManager.isEqualId(id)){
            collectionManager.removeLower(object);
        }
        PrintMsg("removed\n");
        return true;
    }

    @Override
    public String getDescription() {
        return " remove all elements from the collection that are less than the given one\n";
    }
}
