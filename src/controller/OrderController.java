package controller;

import entity.Order;
import service.OrderService;

import java.util.List;

public class OrderController {
    private static OrderService orderService = new OrderService();

    public void add(Order order) {
        orderService.add(order);
    }

    public void update(int id, Order order) {
        orderService.update(id, order);
    }

    public void delete(int id) {
        orderService.delete(id);
    }

    public Order getById(int id) {
        return orderService.getById(id);
    }

    public Order getLast() {
        return orderService.getLast();
    }

    public List<Order> getAll() {
        return orderService.getAll();
    }

    public List<Order> getByUserId(int userId) {
        return orderService.getByUserId(userId);
    }
}
