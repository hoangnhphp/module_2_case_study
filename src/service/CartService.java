package service;

import entity.Cart;
import entity.Customer;
import repository.impl.CartRepository;
import repository.impl.CustomerRepository;

import java.util.List;

public class CartService {

    private static CartRepository cartRepository = new CartRepository();
    public List<Cart> getAll() {
        return cartRepository.getAll();
    }

    public void add(Cart c) {
        cartRepository.save(c);
    }

    public Cart getByUserId(int userId) {
        return cartRepository.getByUserId(userId);
    }
}
