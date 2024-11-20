package controller;

import entity.Cart;
import service.CartService;

import java.util.List;

public class CartController {
    private static CartService cartService = new CartService();

    public List<Cart> getAll() {
        return cartService.getAll();
    }

    public Cart getByUserId(int userId) {
        return cartService.getByUserId(userId);
    }

    public void add(Cart cart) {
        cartService.add(cart);
    }
}
