package de.telran.dzMoisyeyenko210125mbe.model.dto;

import de.telran.dzMoisyeyenko210125mbe.pojo.Cart;
import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {

    private Long cartItemId;
    private Long cartId;
    private Long productId;
    private Integer quantity;
}
