package commands;

import static lib.Reader.PrintMsg;

public class Exit implements Commandable {

    /**
     * execute exit command
     */

    @Override
    public boolean execute(String argument) {
        System.exit(0);
        PrintMsg("i am water :)");
        return true;
    }

    @Override
    public String getDescription() {
        return "end the program (without saving to file)";
    }
}
