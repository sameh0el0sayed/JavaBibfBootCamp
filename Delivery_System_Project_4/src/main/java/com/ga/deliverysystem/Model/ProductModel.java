package com.ga.deliverysystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "Products")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private float price;

    @Column
    private Boolean isAvailable;

    @Column
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @Column
    @JoinColumn(name = "merchantId",referencedColumnName = "id")
    @JsonIgnore
    private MerchantModel merchant;

    @OneToMany(mappedBy ="product" ,cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<OrderItemModel> orderItemModels;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MerchantModel getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantModel merchant) {
        this.merchant = merchant;
    }

    public List<OrderItemModel> getOrderItemModels() {
        return orderItemModels;
    }

    public void setOrderItemModels(List<OrderItemModel> orderItemModels) {
        this.orderItemModels = orderItemModels;
    }
}
