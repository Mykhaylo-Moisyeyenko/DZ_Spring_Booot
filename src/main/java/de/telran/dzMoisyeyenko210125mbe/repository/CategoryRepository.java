package de.telran.dzMoisyeyenko210125mbe.repository;

import de.telran.dzMoisyeyenko210125mbe.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
