package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;
import aoss.assignment.a2.merged.helpers.Connector;
import aoss.assignment.a2.merged.helpers.JsonParser;
import aoss.assignment.a2.merged.models.Item;
import aoss.assignment.a2.merged.models.UserSession;

import java.util.ArrayList;

public class InventoryController {
    public String address;
    private String serverAddress;
    private UserSession session;

    public InventoryController(UserSession session) {
        this.session = session;
        this.address = App.INVENTORY;
    }

    public ArrayList<Item> all() {
        ArrayList<Item> result = new ArrayList<>();
        System.out.println("Access to: " + address);

        String jsonResult = Connector.read(serverAddress + address, session);
        result = JsonParser.parseInventory(jsonResult);

        return result;
    }

    public Item get(String id) {
        String jsonResult = Connector.read(serverAddress + address + "/" + id, session);
        Item result = JsonParser.parseItem(jsonResult);

        return result;
    }

    public Item create(Item item) {
        System.out.println("Access to: " + address);
        String itemString = JsonParser.fromItem(item);
        String jsonResult = Connector.post(serverAddress + address, itemString, session);

        Item result = JsonParser.parseItem(jsonResult);

        return result;
    }

    public Item update(String id, Item item) {
        String itemString = JsonParser.fromItem(item);
        String jsonResult = Connector.put(serverAddress + address + "/" + id, itemString, session);
        System.out.println(jsonResult);
        Item result = JsonParser.parseItem(jsonResult);

        return result;
    }

    public String delete(String id) {
        String result = "";
        result = Connector.delete(serverAddress + address + "/" + id, session);

        return result;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
