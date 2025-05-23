package de.telran.dzMoisyeyenko210125mbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long productId;
    private String name;
    private String description;
    private Double price;
    private String categoryId;
    private String imageUrl;
    private Double discountPrice;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
