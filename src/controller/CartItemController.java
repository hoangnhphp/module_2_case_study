package controller;

import entity.Cart;
import entity.CartItem;
import service.CartItemService;
import service.CartService;

import java.util.List;

public class CartItemController {
    private static CartItemService cartItemService = new CartItemService();

    public List<CartItem> getAll() {
        return cartItemService.getAll();
    }

    public CartItem getByProductIdAndCartId(int productId, int cartId) {
        return cartItemService.getByProductIdAndCartId(productId, cartId);
    }

    public void add (CartItem cart) {
        cartItemService.add(cart);
    }

    public void update (int id, CartItem item) {
        cartItemService.update(id, item);
    }

    public List<CartItem> getByCartId(int cartId) {
        return cartItemService.getByCartId(cartId);
    }

    public void delete (int id) {
        cartItemService.delete(id);
    }

    public CartItem getLast() {
        return cartItemService.getLast();
    }

    public List<CartItem> getByProductId(int productId) {
        return cartItemService.getByProductId(productId);
    }
}
