package aoss.assignment.restservice.services.orderinfo;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.orderinfo.Order;
import aoss.assignment.restservice.repos.orderinfo.OrdersInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersInfoService {

    private final OrdersInfoRepo ordersInfoRepo;

    public OrdersInfoService(OrdersInfoRepo ordersInfoRepo) {
        this.ordersInfoRepo = ordersInfoRepo;
    }

    public List<Order> findAll() {
        return ordersInfoRepo.findAll();
    }

    public Order findById(Integer id) {
        return ordersInfoRepo.findById(id);
    }

    public Order create(Order order) {
        return ordersInfoRepo.create(order);
    }

    public Order updateById(Integer id, Order byId) {
        return ordersInfoRepo.updateById(id, byId);
    }

    public void deleteById(Integer id) {
        ordersInfoRepo.deleteById(id);
    }
}
