package commands;

import models.Ticket;
import lib.CollectionManager;
import lib.ConsoleManager;

import static lib.ConsoleManager.PrintMsg;

public class AddMin implements Commandable {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;

    public AddMin(CollectionManager collectionManager, ConsoleManager consoleManager) {
        this.collectionManager = collectionManager;
        this.consoleManager = consoleManager;
    }

    /**
     * execute addIfMin command
     * @param argument ticket id
     */

    @Override
    public boolean execute(String argument) {
        Integer id;
        id = consoleManager.readInteger("Enter an id!");
        Ticket object = consoleManager.getTicketObj(id);
        if (!collectionManager.isEqualId(id)) {
            collectionManager.addMin(object);
        } else {
            PrintMsg("such an ID already exists, alas");
        }
        PrintMsg("min item added\n");
        return true;
    }

    @Override
    public String getDescription() {
        return " add a new item to a collection if its value is less than the smallest item in this collection\n";
    }
}
