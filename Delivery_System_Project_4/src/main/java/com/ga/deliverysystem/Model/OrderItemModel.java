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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}
