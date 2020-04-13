package aoss.assignment.a2.merged.models;

public class OrderItem {
    private int item_id;
    private String product_id;
    private String description;
    private float item_price;

    public OrderItem(Item item) {
        this.product_id = item.getCode();
        this.description = item.getDescription();
        this.item_price = (float) item.getPrice();
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getItem_price() {
        return item_price;
    }

    public void setItem_price(float item_price) {
        this.item_price = item_price;
    }

    public String print() {
        return this.product_id + ":" + this.description + ":$" + this.item_price;
    }
}
