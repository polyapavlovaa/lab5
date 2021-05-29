package lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Ticket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static lib.Reader.PrintErr;
import static lib.Reader.PrintMsg;

public class DataWriter {
    private final String outputPath;

    public DataWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * saves collection to file
     * @param tickets
     */
    public void saveData(Vector<Ticket> tickets) {

        /*GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.setPrettyPrinting().create();*/

        Gson gson = new Gson();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
            writer.write(gson.toJson(tickets));
            PrintMsg("Collection saved successfully\n");
            writer.close();
        } catch (IOException e) {
            PrintErr("there is no file to save");
        }
    }
}
