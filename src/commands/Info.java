package commands;

import lib.CollectionManager;

import static lib.Reader.PrintMsg;

public class Info implements Commandable {
    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     * execute info command
     */

    @Override
    public boolean execute(String argument) {
        PrintMsg(collectionManager.getInformation() + " \ninformation received\n");
        return true;
    }

    @Override
    public String getDescription() {
        return "print information about the collection to the stdout: type, date of initialization, number of elements";
    }
}
