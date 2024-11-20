package service;

import entity.Cloth;
import entity.Customer;
import repository.impl.CustomerRepository;

import java.util.List;

public class CustomerService {
    private static CustomerRepository customerRepository = new CustomerRepository();
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public void add(Customer c) {
        customerRepository.save(c);
    }

    public Customer getByEmailAndPassword(String email, String password) {
        return customerRepository.getByEmailAndPassword(email, password);
    }

    public void update(int id, Customer customer) {
        customerRepository.update(id, customer);
    }
}
