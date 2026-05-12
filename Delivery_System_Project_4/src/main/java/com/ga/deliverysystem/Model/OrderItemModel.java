package com.ga.deliverysystem.Model;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

@Entity
@Table(name = "OrderItems")
public class OrderItemModel {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Float quantity;

    @Column
    private Float unitPrice;

    @ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "OrderId",referencedColumnName = "Id")
    private OrderModel order;

    @ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "ProductId",referencedColumnName = "Id")
    private ProductModel product;

}
