package de.telran.dzMoisyeyenko210125mbe.model.dto;

import de.telran.dzMoisyeyenko210125mbe.pojo.Status;
import de.telran.dzMoisyeyenko210125mbe.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Long orderId;
    private Long userId;
    private Timestamp createdAt;
    private String deliveryAddress;
    private String contactPhone;
    private String deliveryMethod;
    private String status;
    private Timestamp updatedAt;
}
