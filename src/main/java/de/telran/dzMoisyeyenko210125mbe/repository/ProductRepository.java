package de.telran.dzMoisyeyenko210125mbe.repository;

import de.telran.dzMoisyeyenko210125mbe.model.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(nativeQuery = true, value = "select * from products as p where p.name = ?1")
    List<ProductEntity> findByName (String valueName);

    @Query(nativeQuery = true, value = "select * from products as p where p.discountprice is not null")
    List<ProductEntity> findByDiscount();

    //1. Напишите методы в Репозитории для редактирования цены товара и дисконта
    //в таблице Product c использованием @Query.
    @Modifying
    @Transactional
    @Query(value = "update ProductEntity p set p.price=:newPrice,p.discountPrice=:newDiscountPrice where p.productId=:id")
    int setPriceAndDiscountOfProduct(Long id, Double newPrice, Double newDiscountPrice);

//    Используя механизм "Spring Data JPA: запросы, генерируемые по имени метода":
//    1) * напишите метод, позволяющий найти продукты, в описании которых (Description)
//    есть слово "Распродажа" и цена которых более 100 евро.
    List<ProductEntity> findByDescriptionContainingAndPriceGreaterThan(String description, Double price);

//    2) ** напишите метод, позволяющий найти продукты, категорию которых мы можем задать через
//    первый аргумент метода и которые имеют дисконтную цену (DiscountPrice > 0).
    List<ProductEntity> findByCategoryNameAndDiscountPriceGreaterThan(String categoryName, Double discountPrice);


}
