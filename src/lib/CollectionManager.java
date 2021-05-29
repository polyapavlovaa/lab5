package lib;

import models.Ticket;
import models.TicketType;

import java.time.ZonedDateTime;
import java.util.*;

import static lib.ConsoleManager.PrintMsg;

public class CollectionManager {
    private Vector<Ticket> tickets;
    private final DataWriter dataWriter;
    private final DataReader dataReader;


    /*public CollectionManager(){
    }*/


    public CollectionManager(DataReader dataReader, DataWriter dataWriter) {
        this.dataReader = dataReader;
        this.tickets = dataReader.readData();
        this.dataWriter = dataWriter;
    }

    public String getInformation() {
        String dataSimpleName = tickets.getClass().getSimpleName();
        ZonedDateTime creationDate = ZonedDateTime.now();
        String size = String.valueOf(tickets.size());
        String res = "";
        res += "\n" + "collection type: " + dataSimpleName + "\n";
        res += "collection creation time: " + creationDate.toString() + "\n";
        res += "number of items in the collection: " + size + "\n";
        return res;
    }

    public String getStringElements() {
        StringBuilder list = new StringBuilder();
        tickets.forEach(t ->
                list.append(t.toString()).append("\n")
        );
        return list.toString();
    }


    public Vector<Ticket> getTickets() {
        return tickets;
    }

    public void save() {
        dataWriter.saveData(tickets);
    }

    public Integer getID() {
        int maxID = 0;
        for (Ticket t : tickets) {
            int id = t.getId();
            if (maxID < id) {
                maxID = id;
            }
        }
        return maxID + 1;
    }

    public void update(Ticket update) {
        Vector<Ticket> res = new Vector<>();
        for (Ticket t : tickets) {
            if (!t.getId().equals(update.getId())) {
                res.add(t);
            }
        }
        res.add(update);
        tickets = res;
    }


    public void addItem(Ticket ticket) {
        for (Ticket t : tickets) {
            tickets.add(ticket);
            PrintMsg("added\n");
            return;
        }
    }


    public void addMin(Ticket ticket) {
        for (Ticket t : tickets) {
            if (ticket.getId() > t.getId()) {
                PrintMsg("you cannot add\n");
                return;
            }
        }
        tickets.add(ticket);
    }


    public boolean isEqualId(Integer ID) {
        for (Ticket t : tickets) {
            if (t.getId().equals(ID)) {
                return true;
            }
        }
        return false;
    }


    public void removeID(Integer id) {
        for (Ticket t : tickets) {
            if (t.getId().equals(id)) {
                tickets.remove(t);
                return;
            }
        }
    }

    public void removeLower(Ticket ticket) {
        for (Ticket t : tickets) {
            if (ticket.getId() > t.getId()) {
                PrintMsg("cannot be removed\n");
                return;
            }
        }
        tickets.remove(ticket);
    }

    public String startsWithSubstring(String substring) {
        StringBuilder list = new StringBuilder();
        for (Ticket t : tickets) {
            if (t.getName().startsWith(substring.trim())) {
                list.append(t.toString()).append("\n");
            }
        }
        return list.toString();
    }

    public String containsSomeSubstring(String substring) {
        StringBuilder list = new StringBuilder();
        for (Ticket t : tickets) {
            if (t.getName().contains(substring.trim())) {
                list.append(list).append(t.toString()).append("\n");
            }
        }
        return list.toString();
    }

    public void assort(TicketType ticketType) {

        List<String> res = new Vector<>();
        for (Ticket t : tickets) {
            if (t.getType().equals(ticketType)) {
                res.add(t.toString());
            }
            int count = res.size();
            PrintMsg(Integer.toString(count));
        }
    }
}
