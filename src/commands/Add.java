package commands;

import lib.CollectionManager;
import lib.ConsoleManager;
import models.Ticket;

import static lib.Reader.PrintMsg;

public class Add implements Commandable {
    private final CollectionManager collectionManager;
    private final ConsoleManager consoleManager;

    public Add(CollectionManager collectionManager, ConsoleManager consoleManager) {
        this.collectionManager = collectionManager;
        this.consoleManager = consoleManager;
    }

    /**
     * execute add command
     */

    @Override
    public boolean execute(String argument) {
        Ticket item = consoleManager.getTicketObj(collectionManager.getID());
        collectionManager.addItem(item);
        PrintMsg("the new item has been successfully added to the collection\n");
        return true;
    }


    @Override
    public String getDescription() {
        return "add new element to collection\n";
    }
}
