package de.telran.dzMoisyeyenko210125mbe.repository;

import de.telran.dzMoisyeyenko210125mbe.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(nativeQuery = true, value = "select * from products as p where p.name = ?1")
    List<ProductEntity> findByName (String valueName);

    @Query(nativeQuery = true, value = "select * from products as p where p.discountprice is not null")
    List<ProductEntity> findByDiscount();
}
