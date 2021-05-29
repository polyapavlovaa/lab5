package lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import models.Ticket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

import static lib.Reader.PrintErr;
import static lib.Reader.PrintMsg;

public class DataReader {
    private final String inputPath;

    public DataReader(String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * the collection to be loaded into the file
     * @return vector
     */
    public Vector<Ticket> readData() {
        if (inputPath == null) {
            return new Vector<>();
        }
        /*GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.create();*/

        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputPath));
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
        } catch (IOException e) {
            PrintErr("file access error");
        }
        return new Vector<>();
    }

    public void checkValues (){
        Vector<Ticket> vector = readData();
        for (Ticket eachTicket: vector){
//            if()
        }
    }

}
