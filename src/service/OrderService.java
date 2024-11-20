package service;

import entity.Order;
import repository.impl.OrderRepository;

import java.util.List;

public class OrderService {
    private static OrderRepository orderRepository = new OrderRepository();

    public void add(Order order) {
        orderRepository.save(order);
    }

    public void update(int id, Order order) {
        orderRepository.update(id, order);
    }

    public void delete(int id) {
        orderRepository.delete(id);
    }

    public Order getById(int id) {
        return orderRepository.findById(id);
    }

    public Order getLast() {
        return orderRepository.getLast();
    }

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public List<Order> getByUserId(int userId) {
        return orderRepository.getByUserId(userId);
    }
}
