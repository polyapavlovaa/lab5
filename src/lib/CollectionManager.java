package lib;

import models.*;

import java.time.ZonedDateTime;
import java.util.*;

import static lib.ConsoleManager.PrintMsg;

public class CollectionManager {
    private Vector<Ticket> tickets;
    private final FileManager fileManager;


    /*public CollectionManager(){
    }*/


    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.tickets = fileManager.readData();
        fileManager.checkData(tickets);
    }

    public String getInformation() {
        String dataSimpleName = tickets.getClass().getSimpleName();
        ZonedDateTime creationDate = ZonedDateTime.now();
        String size = String.valueOf(tickets.size());
        String res = "";
        res += "\n" + "collection type: " + dataSimpleName + "\n";
        res += "collection creation time: " + creationDate + "\n";
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
        fileManager.saveData(tickets);
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
                list.append(t).append("\n");
            }
        }
        return list.toString();
    }

    public String containsSomeSubstring(String substring) {
        StringBuilder list = new StringBuilder();
        for (Ticket t : tickets) {
            if (t.getName().contains(substring.trim())) {
                list.append(list).append(t).append("\n");
            }
        }
        return list.toString();
    }

//      too lazy to think about how to make it more abstract
//       maybe this method will be changed
    public void assort() {

        Vector<Ticket> res1 = new Vector<>();
        Vector<Ticket> res2 = new Vector<>();
        Vector<Ticket> res3 = new Vector<>();
        for (Ticket t : tickets) {
            if (t.getType().equals(TicketType.USUAL)) {
                res1.add(t);
            }
            if (t.getType().equals(TicketType.CHEAP)) {
                res2.add(t);
            }
            if (t.getType().equals(TicketType.BUDGETARY)) {
                res3.add(t);
            }
        }
        System.out.println(res1);
        PrintMsg("Number of elements " +"(type - "+ TicketType.USUAL + "): " + res1.size());

        System.out.println(res2);
        PrintMsg("Number of elements " +"(type - "+ TicketType.CHEAP + "): " + res2.size());

        System.out.println(res3);
        PrintMsg("Number of elements " +"(type - "+ TicketType.BUDGETARY + "): " + res3.size());

    }
}
