package com.ga.deliverysystem.Model;


import com.ga.deliverysystem.Model.Enum.OrderStatusEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orders")
public class OrderModel {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String deliveryAddress;

    @Column(name = "OrderStatus")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;

    @Column
    private Float orderTotal;

    @Column
    private LocalDateTime placedAt;

    @Column
    private LocalDateTime deliveredAt;

    @ManyToOne(
            cascade = CascadeType.REFRESH,fetch = FetchType.EAGER
    )
    @JoinColumn(name = "UserId",referencedColumnName = "id")
    private UserModel user;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.REMOVE,fetch = FetchType.EAGER
    )
     private List<OrderItemModel> orderItems;

    @OneToOne(
            cascade = CascadeType.REFRESH,fetch = FetchType.EAGER
    )
    @JoinColumn(name = "DriverId",referencedColumnName = "id")
    private DriverModel driver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Float orderTotal) {
        this.orderTotal = orderTotal;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(LocalDateTime placedAt) {
        this.placedAt = placedAt;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<OrderItemModel> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemModel> orderItems) {
        this.orderItems = orderItems;
    }

    public DriverModel getDriver() {
        return driver;
    }

    public void setDriver(DriverModel driver) {
        this.driver = driver;
    }
}
