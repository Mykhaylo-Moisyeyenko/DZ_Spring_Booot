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
@Table(name = "Favorites")
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoriteID")
    private Long favoriteId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductEntity product;

}
