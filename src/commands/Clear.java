package commands;

import lib.CollectionManager;

import static lib.Reader.PrintMsg;

public class Clear implements Commandable {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager){
        this.collectionManager=collectionManager;
    }

    /**
     * execute clear command
     */
    @Override
    public boolean execute(String argument) {
        collectionManager.getTickets().clear();
        PrintMsg("collection cleaned successfully");
        return true;
    }

    @Override
    public String getDescription() {
        return " clear collection\n";
    }
}
