package commands;

import lib.CollectionManager;

import static lib.Reader.PrintMsg;

public class Show implements Commandable {
    CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * execute show command
     */

    @Override
    public boolean execute(String argument) {
        PrintMsg(collectionManager.getStringElements() + "\nshowed\n");
        return true;
    }

    @Override
    public String getDescription() {
        return "print to stdout all elements of the collection in string representation";
    }
}
