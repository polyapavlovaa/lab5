package lib;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import exceptions.LackOfAccessException;
import models.Location;
import models.Ticket;
import models.Venue;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Vector;

import static lib.Reader.PrintErr;
import static lib.Reader.PrintMsg;

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
            if (!Files.isReadable(Paths.get(path))){
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
            PrintMsg("Collection loaded successfully");
            return data;
        } catch (FileNotFoundException e) {
            PrintErr(" file not found");
        } catch (NoSuchElementException e) {
            PrintErr("file is empty");
        } catch (JsonParseException e) {
            PrintErr("Another collection in the file");
        }catch (LackOfAccessException e){
            PrintErr("no read rights");
        }
        catch (IOException e) {
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
            if (Files.isWritable(Paths.get(path))){
                throw new LackOfAccessException();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(gson.toJson(tickets));
            PrintMsg("Collection saved successfully\n");
            writer.close();
        } catch (LackOfAccessException e){
            PrintErr("no write rights");
        }
        catch (IOException e) {
            PrintErr("there is no file to save");
        }
    }

    public boolean isGoodData() {
        Vector<Ticket> tickets = readData();
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

            if (name.isEmpty() | nameV.isEmpty() | xLoc == null | yLoc == null | zLoc == null | nameLoc == null | zipCode == null | time == null | y == null) {
                PrintErr(" empty/null value");
                return false;
            }
            if (id < 0 | price < 0 | idV < 0 | capacity < 0) {
                PrintErr(" negative or zero value");
                return false;
            }
            if (x > 518 | y > 332) {
                PrintErr("value is greater than the maximum (x_max=518;y_max=332)");
            }
        }
        return true;
    }
}
