package entity;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {
    private int id;
    private int cartId;
    private int productId;
    private int quantity;

    public CartItem() {}
    public CartItem(int id, int cartId, int productId, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id && cartId == cartItem.cartId && productId == cartItem.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartId, productId, quantity);
    }
}
