package de.telran.dzMoisyeyenko210125mbe.repository;

import de.telran.dzMoisyeyenko210125mbe.model.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
