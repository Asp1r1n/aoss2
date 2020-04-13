package aoss.assignment.a2.merged.controllers.orders;

import aoss.assignment.a2.merged.helpers.App;
import aoss.assignment.a2.merged.helpers.Connector;
import aoss.assignment.a2.merged.helpers.JsonParser;
import aoss.assignment.a2.merged.models.Item;
import aoss.assignment.a2.merged.models.Order;
import aoss.assignment.a2.merged.models.OrderItem;

import java.util.ArrayList;

public class OrderController {
    public String address;

    public OrderController() {
        this.address = App.ORDER;
    }

    public Order postOrder(Order order, ArrayList<OrderItem> orderItems) {
        String orderString = JsonParser.fromOrder(order);
        String jsonResult = Connector.post(address, orderString);

        Order result = JsonParser.parseOrder(jsonResult);

        //Create ordertable
        Connector.post(address + "items/" + order.getOrdertable(), "");

        //Input order item to ordertable
        for (OrderItem orderItem : orderItems) {
            String orderItemString = JsonParser.fromOrderItem(orderItem);
            Connector.post(address + "items/" + order.getOrdertable() + "/add", orderItemString);
        }

        return result;
    }

}
