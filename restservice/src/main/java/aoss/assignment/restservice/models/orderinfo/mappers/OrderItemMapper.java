package aoss.assignment.restservice.models.orderinfo.mappers;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 13.04.2020 */

import aoss.assignment.restservice.models.orderinfo.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {

        OrderItem orderItem = new OrderItem();
        orderItem.setId(resultSet.getInt("item_id"));
        orderItem.setProductId(resultSet.getString("product_id"));
        orderItem.setDescription(resultSet.getString("description"));
        orderItem.setPrice(resultSet.getDouble("item_price"));

        return orderItem;
    }
}
