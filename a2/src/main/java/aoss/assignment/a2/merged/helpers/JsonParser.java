package aoss.assignment.a2.merged.helpers;

import aoss.assignment.a2.merged.models.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonParser {
    public static ArrayList<Item> parseInventory(String json){
        Gson gson = new Gson();
        Type itemType = new TypeToken<ArrayList<Item>>(){}.getType();

        ArrayList<Item> result = gson.fromJson(json, itemType);
        return result;
    }
}
