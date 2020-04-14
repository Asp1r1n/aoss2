package aoss.assignment.a2.merged.controllers.orders;

import aoss.assignment.a2.merged.helpers.App;
import aoss.assignment.a2.merged.helpers.Connector;
import aoss.assignment.a2.merged.helpers.JsonParser;
import aoss.assignment.a2.merged.models.Item;
import aoss.assignment.a2.merged.models.Order;
import aoss.assignment.a2.merged.models.OrderItem;
import aoss.assignment.a2.merged.models.UserSession;

import java.util.ArrayList;

public class OrderController {
    public String address;
    private String serverAddress;
    private UserSession session;

    public OrderController(UserSession session) {
        this.session = session;
        this.address = App.ORDER;
    }

    public Order postOrder(Order order, ArrayList<OrderItem> orderItems) {
        String orderString = JsonParser.fromOrder(order);
        String jsonResult = Connector.post(serverAddress + address, orderString, session);

        Order result = JsonParser.parseOrder(jsonResult);

        //Create ordertable
        Connector.post(serverAddress + address + "items/" + order.getOrdertable(), "", session);

        //Input order item to ordertable
        for (OrderItem orderItem : orderItems) {
            String orderItemString = JsonParser.fromOrderItem(orderItem);
            Connector.post(serverAddress + address + "items/" + order.getOrdertable() + "/add", orderItemString, session);
        }

        return result;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
