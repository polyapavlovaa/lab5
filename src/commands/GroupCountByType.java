package commands;

import lib.CollectionManager;
import lib.ConsoleManager;
import models.TicketType;

import static lib.Reader.PrintMsg;

public class GroupCountByType implements Commandable {
    private final CollectionManager collectionManager;
    private final ConsoleManager consoleManager;


    public GroupCountByType (CollectionManager collectionManager, ConsoleManager consoleManager){
        this.consoleManager=consoleManager;
        this.collectionManager = collectionManager;
    }

    /**
     * execute group_count_by_type command
     * @param argument ticket type as string
     */

    @Override
    public boolean execute(String argument) {
      TicketType ticketType = consoleManager.readEnumType(TicketType.class,"Enter ticket type");
        collectionManager.assort(ticketType);
        PrintMsg("the number of elements is displayed\n");
        return true;
    }

    @Override
    public String getDescription() {
        return " group the elements of the collection by the value of the type field, display the number of elements in the group";
    }
}
