package aoss.assignment.a2.merged.models;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("id")
    private String code;
    @SerializedName("description")
    private String description;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("price")
    private double price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String printItem(String category) {
        return category + ">>" + code + "::" + description + "::" + quantity + "::" + price;
    }
}