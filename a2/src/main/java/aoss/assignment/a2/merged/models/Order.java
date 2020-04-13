package aoss.assignment.a2.merged.models;

public class Order {
    private int order_id;
    private String order_date;
    private String first_name;
    private String last_name;
    private String address;
    private String phone;
    private float total_cost;
    private int shipped;
    private String ordertable;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(float total_cost) {
        this.total_cost = total_cost;
    }

    public int getShipped() {
        return shipped;
    }

    public void setShipped(int shipped) {
        this.shipped = shipped;
    }

    public String getOrdertable() {
        return ordertable;
    }

    public void setOrdertable(String ordertable) {
        this.ordertable = ordertable;
    }
}
