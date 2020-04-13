package aoss.assignment.a2.merged.helpers;

import aoss.assignment.a2.merged.models.Item;
import aoss.assignment.a2.merged.models.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonParser {
    public static ArrayList<Item> parseInventory(String json) {
        Type itemType = new TypeToken<ArrayList<Item>>() {
        }.getType();
        ArrayList<Item> result = instance().fromJson(json, itemType);

        return result;
    }

    public static Item parseItem(String json) {
        Item result = instance().fromJson(json, Item.class);

        return result;
    }

    public static String fromIventory(ArrayList<Item> items) {
        String result = instance().toJson(items);

        return result;
    }

    public static String fromItem(Item item) {
        String result = instance().toJson(item);

        return result;
    }

    public static Order parseOrder(String json) {
        Order result = instance().fromJson(json, Order.class);

        return result;
    }

    public static String fromOrder(Order order) {
        String result = instance().toJson(order);

        return result;
    }

    public static Gson instance() {
        return new Gson();
    }
}
