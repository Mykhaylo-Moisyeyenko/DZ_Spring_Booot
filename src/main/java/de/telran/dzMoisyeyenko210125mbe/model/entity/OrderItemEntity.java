package de.telran.dzMoisyeyenko210125mbe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OrderItems")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderItemID")
    private Long orderItemId;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "PriceAtPurchase")
    private Double priceAtPurchase;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "OrderID")
    private OrderEntity order;
}
