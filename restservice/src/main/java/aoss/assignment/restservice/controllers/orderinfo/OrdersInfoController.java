package aoss.assignment.restservice.controllers.orderinfo;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.CultureBox;
import aoss.assignment.restservice.models.orderinfo.Order;
import aoss.assignment.restservice.services.orderinfo.OrdersInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersInfoController {

    private final OrdersInfoService ordersInfoService;

    public OrdersInfoController(OrdersInfoService ordersInfoService) {
        this.ordersInfoService = ordersInfoService;
    }

    @GetMapping
    public List<Order> list(){
        return ordersInfoService.findAll();
    }

    @GetMapping("{id}")
    public Order getOne(@PathVariable Integer id){
        return ordersInfoService.findById(id);
    }

    @PostMapping
    public Order create(@RequestBody Order order){
        return ordersInfoService.create(order);
    }

    @PutMapping("{id}")
    public Order update(@PathVariable Integer id, @RequestBody Order order){
        Order byId = ordersInfoService.findById(id);
        BeanUtils.copyProperties(order, byId);
        return ordersInfoService.updateById(id, byId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        ordersInfoService.deleteById(id);
    }


}
