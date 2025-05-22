package de.telran.dzMoisyeyenko210125mbe.model.entity;

import de.telran.dzMoisyeyenko210125mbe.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Orders")
public class OrderEntity {

    @Column(name = "OrderID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;

    @Column(name = "DeliveryAddress")
    private String deliveryAddress;

    @Column(name = "ContactPhone")
    private String contactPhone;

    @Column(name = "DeliveryMethod")
    private String deliveryMethod;

    @Column(name = "UpdatedAt")
    private Timestamp updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private UserEntity user;

    @OneToMany(mappedBy = "order")
    private Set<OrderItemEntity> orderItems = new HashSet<>();
}
