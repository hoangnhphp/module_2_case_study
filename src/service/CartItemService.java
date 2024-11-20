package service;

import entity.Cart;
import entity.CartItem;
import repository.impl.CartItemRepository;
import repository.impl.CartRepository;

import java.util.List;

public class CartItemService {

    private static CartItemRepository cartItemRepository = new CartItemRepository();
    public List<CartItem> getAll() {
        return cartItemRepository.getAll();
    }

    public void add(CartItem c) {
        cartItemRepository.save(c);
    }

    public CartItem getByProductIdAndCartId (int productId, int cartId) {
        return cartItemRepository.getByProductIdAndCartId(productId, cartId);
    }

    public void update(int id, CartItem c) {
        cartItemRepository.update(id, c);
    }

    public List<CartItem> getByCartId(int cartId) {
        return cartItemRepository.getByCartId(cartId);
    }

    public void delete(int id) {
        cartItemRepository.delete(id);
    }

    public CartItem getLast() {
        return cartItemRepository.getLast();
    }

    public List<CartItem> getByProductId(int productId) {
        return cartItemRepository.getByProductId(productId);
    }
}
