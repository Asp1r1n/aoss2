package aoss.assignment.a2.merged.models;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class Order {
    @SerializedName("id")
    private int order_id;
    @SerializedName("date")
    private String order_date;
    @SerializedName("firstName")
    private String first_name;
    @SerializedName("lastName")
    private String last_name;
    @SerializedName("address")
    private String address;
    @SerializedName("phone")
    private String phone;
    @SerializedName("totalCost")
    private float total_cost;
    @SerializedName("shipped")
    private int shipped;
    @SerializedName("orderTable")
    private String ordertable;

    public Order(String first_name, String last_name, String address, String phone, float total_cost) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.phone = phone;
        this.total_cost = total_cost;
        this.shipped = 0;

        Calendar rightNow = Calendar.getInstance();

        int TheHour = rightNow.get(rightNow.HOUR_OF_DAY);
        int TheMinute = rightNow.get(rightNow.MINUTE);
        int TheSecond = rightNow.get(rightNow.SECOND);
        int TheDay = rightNow.get(rightNow.DAY_OF_WEEK);
        int TheMonth = rightNow.get(rightNow.MONTH);
        int TheYear = rightNow.get(rightNow.YEAR);

        this.order_date = TheMonth + "/" + TheDay + "/" + TheYear + " "
                + TheHour + ":" + TheMinute + ":" + TheSecond;

        this.ordertable = "order" + String.valueOf(rightNow.getTimeInMillis());
    }

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
