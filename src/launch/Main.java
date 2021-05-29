package launch;

import commands.*;
import exceptions.NoSuchCommandException;
import lib.*;
import models.Ticket;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.Vector;

import static lib.Reader.PrintErr;

public class Main {

    public static void main(String[] args)  {

//        String path = System.getenv().get("MILKY_WAY");

        String path = "out.json";
        Scanner scanner = new Scanner(System.in);
        DataReader dataReader = new DataReader(path);
        DataWriter dataWriter = new DataWriter(path);


        /*Vector<Ticket> tickets = new Vector<>();
        ConsoleManager cons = new ConsoleManager(scanner);
        tickets.add(cons.getTicketObj(6));
        dataWriter.saveData(tickets);*/

        Executor executor = new Executor(scanner,dataReader,dataWriter);
        try {
            executor.run();
        } catch (NoSuchCommandException e) {
            PrintErr("No such command");
        }
    }
}
