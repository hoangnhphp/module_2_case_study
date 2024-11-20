package controller;

import entity.Cloth;
import entity.Customer;
import service.CustomerService;

import java.io.*;
import java.util.List;

public class CustomerController {
    private static CustomerService customerService = new CustomerService();
    public static final String SRC_SESSION = "src/data/session.dat";

    public List<Customer> getAll() {
        return customerService.getAll();
    }

    public void add (Customer c) {
        customerService.add(c);
    }

    public int login (String email, String password) {
        Customer c = customerService.getByEmailAndPassword(email, password);
        if (c != null) {
            saveSession(c);
            return 1;
        } else {
            return 0;
        }
    }

    public void saveSession(Customer c) {
        File file = new File(SRC_SESSION);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(c);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Looix khong tim thay file");
        } catch (IOException e) {
            System.out.println("loi ghi file");
        }
    }

    public Customer readFileBinary() {
        File file = new File(SRC_SESSION);
        Customer c = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            c = (Customer) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Loi ko tim thay file");
        } catch (IOException e) {
            if (e.getMessage() != null) {
                System.out.println("CustomerController:" + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Loi khong tim thay class");
        }
        return c;
    }

    public void update(int id, Customer customer) {
        customerService.update(id, customer);
    }
}
