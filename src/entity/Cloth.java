package entity;

import java.io.Serializable;
import java.util.Objects;

public class Cloth implements Serializable {
    private int id;
    private String name;
    private String color;
    private String size;
    private char genderType;
    private int quantity;
    private int price;

    public Cloth() {}
    public Cloth(int id, String name, String color, String size, char genderType, int quantity, int price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.genderType = genderType;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public char getGenderType() {
        return genderType;
    }

    public void setGenderType(char genderType) {
        this.genderType = genderType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String text = "Cloth{" +
                "ID: " + id +
                ", Tên: " + name +
                ", Màu: " + color +
                ", Cỡ: " + size +
                ", Giá: " + price + "VND";
        if (!isAvailable()) {
            text += "Hết Hàng }";
        } else {
            text += "}";
        }

        return text;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cloth cloth = (Cloth) o;
        return id == cloth.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
