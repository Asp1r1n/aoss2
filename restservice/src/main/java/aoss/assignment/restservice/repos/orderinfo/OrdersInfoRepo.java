package aoss.assignment.restservice.repos.orderinfo;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.orderinfo.Order;
import aoss.assignment.restservice.models.orderinfo.mappers.OrderRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersInfoRepo {

    private final JdbcTemplate database;

    public OrdersInfoRepo(@Qualifier("orderInfoJdbcTemplate") JdbcTemplate database) {
        this.database = database;
    }


    public List<Order> findAll() {
        String sql = "select * from orders";
        return database.query(sql, new OrderRowMapper());
    }


    public Order findById(Integer id) {
        String sql = "select * from orders where order_id = ?";
        return database.queryForObject(sql, new Object[]{id}, new OrderRowMapper());
    }


    public Order create(Order order) {
        String sql = "insert into orders(order_date,first_name,last_name,address,phone,total_cost,shipped,ordertable)" +
                "values (?,?,?,?,?,?,?,?)";
        database.update(sql, order.getDate(), order.getFirstName(), order.getLastName(), order.getAddress(),
                order.getPhone(),order.getTotalCost(),order.getShipped(),order.getOrderTable());
        String sql2 = "select * from orders where order_id = (select order_id from orders order by order_id desc limit 1)";
        return database.queryForObject(sql2, new OrderRowMapper());
    }

    public Order updateById(Integer id, Order byId) {
        String sql = "update orders set order_date = ? , first_name = ?, last_name = ?, address = ?, phone = ?," +
                "total_cost = ?, shipped = ?, ordertable = ? where order_id = ?";
        database.update(sql, byId.getDate(), byId.getFirstName(), byId.getLastName(), byId.getAddress(),
                byId.getPhone(), byId.getTotalCost(), byId.getShipped(), byId.getOrderTable(), id);
        return byId;
    }


    public void deleteById(Integer id) {
        String sql = "delete from orders where order_id = ?";
        database.update(sql, id);
    }
}
