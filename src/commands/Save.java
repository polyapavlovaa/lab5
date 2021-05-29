package commands;

import lib.CollectionManager;

import static lib.Reader.PrintMsg;

public class Save implements Commandable {
    private final CollectionManager collectionManager;

    public Save(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     * execute save command
     */

    @Override
    public boolean execute(String argument) {
        collectionManager.save();
        PrintMsg("saved\n");
        return true;
    }

    @Override
    public String getDescription() {
        return "save collection to file";
    }
}
