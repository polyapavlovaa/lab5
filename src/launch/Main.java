package launch;

import commands.*;
import exceptions.NoSuchCommandException;
import lib.*;
import models.Ticket;

import java.util.Scanner;
import java.util.Vector;


import static lib.Reader.PrintErr;

public class Main {

    public static void main(String[] args)  {

        String path = System.getenv().get("MILKY_WAY");

//        String path = "out.json";
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager(path);

        Executor executor = new Executor(scanner,fileManager);
        try {
            executor.run();
        } catch (NoSuchCommandException e) {
            PrintErr("No such command");
        }
    }
}
