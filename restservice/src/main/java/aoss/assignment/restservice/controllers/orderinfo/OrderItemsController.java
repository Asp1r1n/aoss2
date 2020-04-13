package aoss.assignment.restservice.controllers.orderinfo;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 13.04.2020 */

import aoss.assignment.restservice.models.orderinfo.Order;
import aoss.assignment.restservice.models.orderinfo.OrderItem;
import aoss.assignment.restservice.services.orderinfo.OrderItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/items")
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @GetMapping("/{table}")
    public List<OrderItem> list(@PathVariable String table){
        return orderItemsService.findAll(table);
    }

    @GetMapping("/{table}/{id}")
    public OrderItem getOne(@PathVariable String table , @PathVariable Integer id){
        return orderItemsService.findByItemId(table,id);
    }

    @PostMapping("/{table}")
    public List<OrderItem> create(@PathVariable String table){
        return orderItemsService.createTable(table);
    }

    @PostMapping("/{table}/add")
    public OrderItem create(@PathVariable String table, @RequestBody OrderItem orderItem){
        return orderItemsService.create(table, orderItem);
    }

    @PutMapping("/{table}/{id}")
    public OrderItem update(@PathVariable String table, @PathVariable Integer id, @RequestBody OrderItem orderItem){
        OrderItem byId = orderItemsService.findByItemId(table,id);
        BeanUtils.copyProperties(orderItem, byId);
        return orderItemsService.updateByItemId(table,id, byId);
    }

    @DeleteMapping("/{table}/{id}")
    public void delete(@PathVariable String table, @PathVariable Integer id){
        orderItemsService.deleteByItemId(table, id);
    }

    @DeleteMapping("/{table}")
    public void delete(@PathVariable String table){
        orderItemsService.deleteTable(table);
    }
}
