package aoss.assignment.restservice.services.orderinfo;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 13.04.2020 */

import aoss.assignment.restservice.models.orderinfo.OrderItem;
import aoss.assignment.restservice.repos.orderinfo.OrderItemsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsService {

    private final OrderItemsRepo orderItemsRepo;

    public OrderItemsService(OrderItemsRepo orderItemsRepo) {
        this.orderItemsRepo = orderItemsRepo;
    }

    public List<OrderItem> findAll(String table) {
        return orderItemsRepo.findAll(table);
    }

    public OrderItem findByItemId(String table, Integer id) {
        return orderItemsRepo.findByItemId(table,id);
    }

    public List<OrderItem> createTable(String table) {
        return orderItemsRepo.createTable(table);
    }

    public OrderItem create(String table, OrderItem orderItem) {
        return orderItemsRepo.create(table, orderItem);
    }

    public OrderItem updateByItemId(String table, Integer id, OrderItem byId) {
        return orderItemsRepo.updateByItemId(table,id,byId);
    }

    public void deleteByItemId(String table, Integer id) {
        orderItemsRepo.deleteByItemId(table, id);
    }

    public void deleteTable(String table) {
        orderItemsRepo.deleteTable(table);
    }
}
