package lib;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import exceptions.*;
import models.*;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

import static lib.Reader.*;

public class FileManager {
    private final String path;

    public FileManager(String path) {
        this.path = path;
    }

    /**
     * the collection to be loaded into the file
     *
     * @return vector
     */
    public Vector<Ticket> readData() {
        if (path == null) {
            return new Vector<>();
        }
        /*GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.create();*/

        Gson gson = new Gson();
        try {
            if (!Files.isReadable(Paths.get(path))) {
                throw new LackOfAccessException();
            }
            BufferedReader reader = new BufferedReader(new FileReader(path));
            Type dataType = new TypeToken<Vector<Ticket>>() {
            }.getType();
            String json = reader.readLine();
            if (json == null) {
                return new Vector<>();
            }
            Vector<Ticket> data = gson.fromJson(json.trim(), dataType);
            PrintMsg("Collection read from file");
            return data;
        } catch (FileNotFoundException e) {
            PrintErr(" file not found");
        } catch (NoSuchElementException e) {
            PrintErr("file is empty");
        } catch (JsonParseException e) {
            PrintErr("Another collection in the file");
        } catch (LackOfAccessException e) {
            PrintErr("no read rights");
        } catch (IOException e) {
            PrintErr("file access error");
        }
        return new Vector<>();
    }

    /**
     * saves collection to file
     *
     * @param tickets is a collection to save
     */
    public void saveData(Vector<Ticket> tickets) {

        /*GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.setPrettyPrinting().create();*/

        Gson gson = new Gson();
        try {
            if (!Files.isWritable(Paths.get(path))) {
                throw new LackOfAccessException();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(gson.toJson(tickets));
            PrintMsg("Collection saved successfully\n");
            writer.close();
        } catch (LackOfAccessException e) {
            PrintErr("no write rights");
        } catch (IOException e) {
            PrintErr("there is no file to save");
        }
    }

    /**
     * additional check if the collection in the file was changed manually
     * @param tickets collection<Ticket></Ticket>
     */

    public void checkData(Vector<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            int id = ticket.getId(); //>0
            String name = ticket.getName(); //not null/empty
            LocalDateTime time = ticket.getCreationDate(); //not null
            int price = ticket.getPrice(); //>0

            double x = ticket.getCoordinates().getX(); //max 518
            Integer y = ticket.getCoordinates().getY(); //max 332 not null

            Venue venue = ticket.getVenue();
            long idV = venue.getId(); //>0
            String nameV = venue.getName(); //not null/empty
            Integer capacity = venue.getCapacity(); //>0

            String zipCode = venue.getAddress().getZipCode(); //not null

            Location location = venue.getAddress().getTown();
            Float xLoc = location.getX(); //not null
            Integer yLoc = location.getY(); //not null
            Integer zLoc = location.getZ(); //not null
            String nameLoc = location.getName(); //not null

            try {
                if (name.equals("") | nameV.equals("") | xLoc == null | yLoc == null | zLoc == null | nameLoc == null | zipCode == null | time == null | y == null) {
                    throw new EmptyIOException();
                }
                if (id < 0 | price < 0 | idV < 0 | capacity < 0) {
                    PrintMsg(" negative or zero value");
                    throw new IncorrectValueException();
                }
                if (x > 518 | y > 332) {
                    PrintMsg(" value is greater than the maximum (x_max=518; y_max=332)");
                    throw new IncorrectValueException();
                }
            } catch (EmptyIOException e) {
                PrintErr(" empty/null value");
                PrintMsg("Working with the collection will be incorrect.+ \n " +
                         "Exit the program.");
                System.exit(0);
            } catch (IncorrectValueException e) {
                PrintErr(" incorrect value");
                PrintMsg("Working with the collection will be incorrect.+ \n " +
                        "Exit the program.");
                System.exit(0);
            }
        }
    }
}
