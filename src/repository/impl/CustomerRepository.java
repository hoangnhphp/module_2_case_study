package repository.impl;


import entity.CartItem;
import entity.Customer;
import repository.ICustomerRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {

    public static final String SRC_CUSTOMER = "src/data/customers.dat";

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        customers = readFileBinary(customers);
        return customers;
    }

    public void save(Customer s) {
        List<Customer> customers = getAll();
        customers.add(s);
        writeFileBinary(customers);
    }

    public void writeFileBinary(List<Customer> customers) {
        File file = new File(SRC_CUSTOMER);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(customers);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Looix khong tim thay file");
        } catch (IOException e) {
            System.out.println("loi ghi file");
        }
    }

    public List<Customer> readFileBinary(List<Customer> customers) {
        File file = new File(SRC_CUSTOMER);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            customers = (List<Customer>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Loi ko tim thay file");
        } catch (IOException e) {
            if (e.getMessage() != null) {
                System.out.println("CustomerRepository: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Loi khong tim thay class");
        }
        return customers;
    }

    public Customer getByEmailAndPassword(String email, String password) {
        List<Customer> customers = getAll();
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    public void update(int id, Customer customer) {
        List<Customer> items = getAll();
        Customer old = getById(id);
        int index = items.indexOf(old);
        items.set(index, customer);
        writeFileBinary(items);
    }

    public Customer getById(int id) {
        List<Customer> customers = getAll();
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
}
