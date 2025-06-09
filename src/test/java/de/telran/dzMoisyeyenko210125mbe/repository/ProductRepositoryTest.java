package de.telran.dzMoisyeyenko210125mbe.repository;

import de.telran.dzMoisyeyenko210125mbe.model.entity.ProductEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepositoryTest;

    private ProductEntity productEntity1;
    private ProductEntity productEntity2;
    private ProductEntity productEntity3;

    @BeforeEach
    void setUp() {

        productEntity1 = ProductEntity.builder()
                .name("pillow")
                .imageUrl("URL")
                .price(15.88)
                .discountPrice(11.00)
                .build();
        productEntity1 = productRepositoryTest.save(productEntity1);

        productEntity2 = ProductEntity.builder()
                .name("pillow2")
                .imageUrl("URL111")
                .price(16.45)
                .discountPrice(14.00)
                .build();
        productEntity2 = productRepositoryTest.save(productEntity2);

        productEntity3 = ProductEntity.builder()
                .name("pillow3")
                .imageUrl("URL333")
                .price(190.12)
                .build();
        productEntity3 = productRepositoryTest.save(productEntity3);
    }

    @AfterEach
    void tearDown() {
        productRepositoryTest.delete(productEntity1);
        productRepositoryTest.delete(productEntity2);
        productRepositoryTest.delete(productEntity3);
    }

    @Test
    void findByNameTest() {

        String expectedName = "pillow";

        List<ProductEntity> productEntityReturn = productRepositoryTest.findByName(expectedName);

        assertNotNull(productEntityReturn);
        assertEquals(1, productEntityReturn.size());
        assertEquals(expectedName, productEntityReturn.getFirst().getName());
    }

    @Test
    void findByDiscountTest() {
        List<ProductEntity> productEntityList = productRepositoryTest.findByDiscount();

        assertEquals(2, productEntityList.size());
        for (ProductEntity productEntity : productEntityList) {
            assertNotNull(productEntity.getDiscountPrice());
        }
    }

    @Test
    void setPriceAndDiscountOfProductTest() {
        Long id = productEntity3.getProductId();
        Double newPrice = 111.11;
        Double newDiscountPrice = 99.99;

        int result = productRepositoryTest.setPriceAndDiscountOfProduct(id, newPrice, newDiscountPrice);
        assertEquals(1, result);

        result = productRepositoryTest.setPriceAndDiscountOfProduct(999L, newPrice, newDiscountPrice);
        assertEquals(0, result);

        ProductEntity productEntityReturn = productRepositoryTest.findById(id)
                .orElse(new ProductEntity());

        assertNotNull(productEntityReturn);
        assertEquals(newPrice, productEntityReturn.getPrice());
        assertEquals(newDiscountPrice, productEntityReturn.getDiscountPrice());
    }
}