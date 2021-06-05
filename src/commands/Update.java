package commands;

import exceptions.EmptyIOException;
import exceptions.NoSuchIdException;
import models.Ticket;
import lib.CollectionManager;
import lib.ConsoleManager;

import static lib.Reader.PrintErr;
import static lib.Reader.PrintMsg;

public class Update implements Commandable {
    private final CollectionManager collectionManager;
    private final ConsoleManager consoleManager;

    public Update(CollectionManager collectionManager, ConsoleManager consoleManager) {
        this.collectionManager = collectionManager;
        this.consoleManager = consoleManager;
    }

    /**
     * execute update command
     * @param argument id
     */

    @Override
    public boolean execute(String argument) {
        int id;
        try {
            if (argument.trim().equals("")) throw new EmptyIOException();
            id = Integer.parseInt(argument.trim());
            if (!collectionManager.isEqualId(id)) throw new NoSuchIdException();
        } catch (NumberFormatException e) {
            PrintErr("You enter not number value");
            return true;
        } catch (EmptyIOException e) {
            PrintErr("You enter null-argument");
            return true;
        } catch (NoSuchIdException e) {
            PrintErr("No such ID in collection");
            return true;
        }
        Ticket ticket = consoleManager.getTicketObj(id);
        collectionManager.update(ticket);
        PrintMsg("updated\n");
        return true;
    }

    @Override
    public String getDescription() {
        return " (id) update the value of the collection element whose id is equal to the given\n";
    }
}
