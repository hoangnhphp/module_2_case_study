package repository.impl;

import entity.Cart;
import repository.ICartRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CartRepository implements ICartRepository {
    public static final String SRC_CART = "src/data/carts.dat";

    public List<Cart> getAll() {
        List<Cart> carts = new ArrayList<>();
        carts = readFileBinary(carts);
        return carts;
    }

    public void save(Cart s) {
        List<Cart> carts = getAll();
        carts.add(s);
        writeFileBinary(carts);
    }

    public void writeFileBinary(List<Cart> carts) {
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

    public List<Cart> readFileBinary(List<Cart> carts) {
        File file = new File(SRC_CART);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            carts = (List<Cart>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Loi ko tim thay file");
        } catch (IOException e) {
            if (e.getMessage() != null) {
                System.out.println("CartRepository: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Loi khong tim thay class");
        }
        return carts;
    }

    public Cart getByUserId(int userId) {
        List<Cart> carts = getAll();
        return carts.stream().filter(c -> c.getUserId() == userId).findFirst().orElse(null);
    }
}
