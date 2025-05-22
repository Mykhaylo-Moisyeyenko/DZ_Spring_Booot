package de.telran.dzMoisyeyenko210125mbe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CartItems")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartItemID")
    private Long cartItemId;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "CartID")
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductEntity product;

}
