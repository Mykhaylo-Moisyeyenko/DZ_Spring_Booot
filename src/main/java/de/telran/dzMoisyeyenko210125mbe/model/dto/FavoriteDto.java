package de.telran.dzMoisyeyenko210125mbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteDto {

    private Long favoriteId;
    private Long userId;
    private Long productId;
}
