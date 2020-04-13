package aoss.assignment.restservice.repos.orderinfo;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 13.04.2020 */

import aoss.assignment.restservice.models.orderinfo.OrderItem;
import aoss.assignment.restservice.models.orderinfo.mappers.OrderItemMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class OrderItemsRepo {

    private final JdbcTemplate database;

    public OrderItemsRepo(@Qualifier("orderInfoJdbcTemplate") JdbcTemplate database) {
        this.database = database;
    }

    public List<OrderItem> findAll(String table) {
        String sql = "select * from " + table;
        return database.query(sql, new OrderItemMapper());
    }

    @SuppressWarnings("SqlResolve")
    public OrderItem findByItemId(String table, Integer id) {
        String sql = "select * from " + table + " where item_id = ?";
        return database.queryForObject(sql, new Object[]{id}, new OrderItemMapper());
    }

    public List<OrderItem> createTable(String table) {
        String sql = "create table " + table + " (item_id int unsigned not null auto_increment primary key," +
                "product_id varchar(20), description varchar(80), item_price float(7,2))";
        database.update(sql);
        return Collections.emptyList();
    }

    @SuppressWarnings("SqlResolve")
    public OrderItem create(String table, OrderItem orderItem) {
        String sql = "insert into " + table + "(product_id,description,item_price) values (?,?,?)";
        database.update(sql, orderItem.getProductId(), orderItem.getDescription(), orderItem.getPrice());
        String sql2 = "select * from " + table + " where item_id = (select item_id from " + table + " order by item_id desc limit 1)";
        return database.queryForObject(sql2, new OrderItemMapper());
    }


    @SuppressWarnings("SqlResolve")
    public OrderItem updateByItemId(String table, Integer id, OrderItem byId) {
        String sql = "update " + table + " set product_id = ?, description = ?, item_price = ? where item_id = ?";
        database.update(sql, byId.getProductId(), byId.getDescription(), byId.getPrice(), id);
        return byId;
    }

    @SuppressWarnings("SqlResolve")
    public void deleteByItemId(String table, Integer id) {
        String sql = "delete from " + table + " where item_id = ?";
        database.update(sql, id);
    }

    public void deleteTable(String table) {
        String sql = "drop table if exists " + table;
        database.update(sql);
    }
}
