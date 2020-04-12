package aoss.assignment.restservice.models.orderinfo.mappers;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.orderinfo.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {

        Order order = new Order();

        order.setId(resultSet.getInt("order_id"));
        order.setDate(resultSet.getString("order_date"));
        order.setFirstName(resultSet.getString("first_name"));
        order.setLastName(resultSet.getString("last_name"));
        order.setAddress(resultSet.getString("address"));
        order.setPhone(resultSet.getString("phone"));
        order.setTotalCost(resultSet.getDouble("total_cost"));
        order.setShipped(resultSet.getInt("shipped"));
        order.setOrderTable(resultSet.getString("ordertable"));

        return order;
    }
}
