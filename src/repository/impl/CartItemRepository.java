package repository.impl;

import entity.Cart;
import entity.CartItem;
import repository.ICartItemRepository;
import repository.ICartRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartItemRepository implements ICartItemRepository {
    public static final String SRC_CART = "src/data/cartItem.dat";

    public List<CartItem> getAll() {
        List<CartItem> items = new ArrayList<>();
        items = readFileBinary(items);
        return items;
    }

    public void save(CartItem i) {
        List<CartItem> carts = getAll();
        carts.add(i);
        writeFileBinary(carts);
    }

    public void writeFileBinary(List<CartItem> carts) {
        File file = new File(SRC_CART);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(carts);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Looix khong tim thay file");
        } catch (IOException e) {
            System.out.println("loi ghi file");
        }
    }

    public List<CartItem> readFileBinary(List<CartItem> carts) {
        File file = new File(SRC_CART);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            carts = (List<CartItem>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Loi ko tim thay file");
        } catch (IOException e) {
            if (e.getMessage() != null) {
                System.out.println(e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Loi khong tim thay class");
        }
        return carts;
    }

    public CartItem getByProductIdAndCartId (int productId, int cartId) {
        List<CartItem> items = getAll();
        for (CartItem item : items) {
            if (item.getProductId() == productId && item.getCartId() == cartId) {
                return item;
            }
        }

        return null;
    }

    public CartItem getById(int id) {
        List<CartItem> items = getAll();
        for (CartItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public List<CartItem> getByCartId(int cartId) {
        List<CartItem> items = getAll();
        List<CartItem> temp = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getCartId() == cartId) {
                temp.add(item);
            }
        }
        return temp;
    }

    public void update(int id, CartItem i) {
        List<CartItem> items = getAll();
        CartItem old = getById(id);
        int index = items.indexOf(old);
        items.set(index, i);
        writeFileBinary(items);
    }

    public void delete(int id) {
        List<CartItem> items = getAll();
        items.removeIf(item -> item.getId() == id);
        writeFileBinary(items);
    }

    public CartItem getLast() {
        List<CartItem> items = getAll();
        return items.isEmpty() ? null : items.get(items.size() - 1);
    }

    public List<CartItem> getByProductId(int productId) {
        List<CartItem> items = getAll();
        List<CartItem> temp = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProductId() == productId) {
                temp.add(item);
            }
        }

        return temp;
    }
}
