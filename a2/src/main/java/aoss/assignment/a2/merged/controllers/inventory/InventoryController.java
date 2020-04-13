package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;
import aoss.assignment.a2.merged.helpers.Connector;
import aoss.assignment.a2.merged.helpers.JsonParser;
import aoss.assignment.a2.merged.models.Item;

import java.util.ArrayList;

public class InventoryController {
    public String address;

    public InventoryController(){
        this.address = App.INVENTORY;
    }

    public ArrayList<Item> all() {
        ArrayList<Item> result = new ArrayList<>();
        System.out.println("Access to: " + address);
        String jsonResult = Connector.read(address);
        result = JsonParser.parseInventory(jsonResult);
        return result;
    }

    public ArrayList<Item> get(String id) {
        ArrayList<Item> result = new ArrayList<>();
        Connector.post(address+"/"+id);
        return result;
    }

    public ArrayList<Item> create(Item item) {
        ArrayList<Item> result = new ArrayList<>();
        Connector.post(address);
        return result;
    }

    public ArrayList<Item> update(String id, Item item) {
        ArrayList<Item> result = new ArrayList<>();
        Connector.post(address);
        return result;
    }
    public ArrayList<Item> delete(String id) {
        ArrayList<Item> result = new ArrayList<>();
        Connector.post(address);
        return result;
    }

}
