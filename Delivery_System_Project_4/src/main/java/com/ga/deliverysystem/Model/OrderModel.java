package com.ga.deliverysystem.Model;


import com.ga.deliverysystem.Model.Enum.OrderStatusEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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
            cascade = CascadeType.REMOVE,fetch = FetchType.EAGER
    )
    @JoinColumn(name = "OrderItemId",referencedColumnName = "id")
    private OrderItemModel orderItem;

    @OneToOne(
            cascade = CascadeType.REFRESH,fetch = FetchType.EAGER
    )
    @JoinColumn(name = "DriverId",referencedColumnName = "id")
    private DriverModel driver;


}
