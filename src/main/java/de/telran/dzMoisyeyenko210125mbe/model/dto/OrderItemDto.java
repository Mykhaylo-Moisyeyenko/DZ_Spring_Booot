package de.telran.dzMoisyeyenko210125mbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {

    private Long orderItemId;
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double priceAtPurchase;
}
