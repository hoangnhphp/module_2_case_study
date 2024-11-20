package repository.impl;

import entity.Cloth;
import entity.Order;
import repository.IOrderRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {

    public static final String SRC_ORDER = "src/data/order.dat";

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        orders = readFileBinary(orders);
        return orders;
    }

    @Override
    public void save(Order o) {
        List<Order> orders = getAll();
        orders.add(o);
        writeFileBinary(orders);
    }

    @Override
    public void writeFileBinary(List<Order> orders) {
        File file = new File(SRC_ORDER);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(orders);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Looix khong tim thay file");
        } catch (IOException e) {
            System.out.println("loi ghi file");
        }
    }

    public List<Order> readFileBinary(List<Order> orders) {
        File file = new File(SRC_ORDER);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            orders = (List<Order>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Loi ko tim thay file");
        } catch (IOException e) {
            if (e.getMessage() != null) {
                System.out.println("OrderRepository: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Loi khong tim thay class");
        }
        return orders;
    }

    public Order findById(int id) {
        List<Order> orders = getAll();
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }

        return null;
    }

    public void update(int id, Order order) {
        List<Order> items = getAll();
        Order old = findById(id);
        int index = items.indexOf(old);
        items.set(index, order);
        writeFileBinary(items);
    }

    public void delete(int id) {
        List<Order> items = getAll();
        items.removeIf(order -> order.getId() == id);
        writeFileBinary(items);
    }

    public Order getLast() {
        List<Order> items = getAll();
        return items.isEmpty() ? null : items.get(items.size() - 1);
    }

    public List<Order> getByUserId(int userId) {
        List<Order> items = getAll();
        List<Order> orders = new ArrayList<>();
        for (Order order : items) {
            if (order.getUserId() == userId) {
                orders.add(order);
            }
        }

        return orders;
    }
}
