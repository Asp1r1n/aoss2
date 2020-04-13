package aoss.assignment.restservice.models.orderinfo;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 13.04.2020 */

public class OrderItem {

    private int id;
    private String productId;
    private String description;
    private double price;

    public OrderItem() {
    }

    public OrderItem(int id, String productId, String description, double price) {
        this.id = id;
        this.productId = productId;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
