package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {
    private int id;
    private int userId;
    private String orderNo;
    private String orderDateTime;
    private String deliveryAddress;
    private String deliveryTime;
    private List<Cloth> detail;
    private int orderStatus;
    private int totalPrice;
    private String remark = null;

    public Order() {}

    public Order(int id, int userId, String orderNo, String orderDateTime, String deliveryAddress, String deliveryTime, List<Cloth> detail, int orderStatus, int totalPrice, String remark) {
        this.id = id;
        this.userId = userId;
        this.orderNo = orderNo;
        this.orderDateTime = orderDateTime;
        this.deliveryAddress = deliveryAddress;
        this.deliveryTime = deliveryTime;
        this.detail = detail;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.remark = remark;
    }

    public Order(int id, int userId, String orderNo, String orderDateTime, String deliveryAddress, List<Cloth> detail, int orderStatus, int totalPrice, String remark) {
        this.id = id;
        this.userId = userId;
        this.orderNo = orderNo;
        this.orderDateTime = orderDateTime;
        this.deliveryAddress = deliveryAddress;
        this.detail = detail;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<Cloth> getDetail() {
        return detail;
    }

    public void setDetail(List<Cloth> detail) {
        this.detail = detail;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderNo='" + orderNo + '\'' +
                ", orderDateTime='" + orderDateTime + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", detail=" + detail +
                ", orderStatus='" + orderStatus + '\'' +
                ", totalPrice=" + totalPrice +
                ", remark='" + remark + '\'' +
                '}';
    }
}
