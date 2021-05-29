package commands;

import lib.CollectionManager;

import java.util.Collections;

import static lib.Reader.PrintMsg;

public class Shuffle implements Commandable {
    private final CollectionManager collectionManager;

    public Shuffle(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * execute shuffle command
     */

    @Override
    public boolean execute(String argument) {
        Collections.shuffle(collectionManager.getTickets());
        PrintMsg("shuffled\n");
        return true;
    }

    @Override
    public String getDescription() {
        return "shuffle the elements of the collection at random";
    }
}
