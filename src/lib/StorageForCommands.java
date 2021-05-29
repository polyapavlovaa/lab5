package lib;

import commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageForCommands {
    List<Commandable> commandsList;

    public StorageForCommands(){
        commandsList = new ArrayList<>();
    }

    public List<Commandable> getCommandsList(DataReader dataReader, DataWriter dataWriter, Scanner scanner){
        CollectionManager collectionManager = new CollectionManager(dataReader,dataWriter);
        ConsoleManager consoleManager = new ConsoleManager(scanner);
        commandsList.add(new Add(collectionManager,consoleManager));
        commandsList.add(new AddMin(collectionManager,consoleManager));
        commandsList.add(new Clear(collectionManager));
        commandsList.add(new Exit());
        commandsList.add(new FilterStartsWithName(collectionManager));
        commandsList.add(new FilterContainsName(collectionManager));
        commandsList.add(new GroupCountByType(collectionManager,consoleManager));
        commandsList.add(new Help(commandsList));
        commandsList.add(new Info(collectionManager));
        commandsList.add(new Remove(collectionManager,consoleManager));
        commandsList.add(new RemoveLower(collectionManager,consoleManager));
        commandsList.add(new Save(collectionManager));
        commandsList.add(new Show(collectionManager));
        commandsList.add(new Shuffle(collectionManager));
        commandsList.add(new Update(collectionManager,consoleManager));
        return commandsList;
    }
}
