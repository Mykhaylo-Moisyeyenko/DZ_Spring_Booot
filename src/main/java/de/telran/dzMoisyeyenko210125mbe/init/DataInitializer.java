package de.telran.dzMoisyeyenko210125mbe.init;

import de.telran.dzMoisyeyenko210125mbe.model.entity.CategoryEntity;
import de.telran.dzMoisyeyenko210125mbe.model.entity.FavoriteEntity;
import de.telran.dzMoisyeyenko210125mbe.model.entity.ProductEntity;
import de.telran.dzMoisyeyenko210125mbe.model.entity.UserEntity;
import de.telran.dzMoisyeyenko210125mbe.model.enums.Role;
import de.telran.dzMoisyeyenko210125mbe.repository.CategoryRepository;
import de.telran.dzMoisyeyenko210125mbe.repository.FavoriteRepository;
import de.telran.dzMoisyeyenko210125mbe.repository.ProductRepository;
import de.telran.dzMoisyeyenko210125mbe.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;

    @PostConstruct
    public void init(){
        CategoryEntity categoryEntity1 = CategoryEntity.builder()
                .name("Pillows")
                .build();
        categoryEntity1 = categoryRepository.save(categoryEntity1);

        ProductEntity productEntity1 = ProductEntity.builder()
                .name("pillow")
                .imageUrl("URL")
                .price(15.88)
                .discountPrice(11.00)
                .description("white pillow 10*10, Распродажа")
                .category(categoryEntity1)
                .build();
        productEntity1 = productRepository.save(productEntity1);

        ProductEntity productEntity2 = ProductEntity.builder()
                .name("pillow2")
                .imageUrl("URL111")
                .price(16.45)
                .discountPrice(14.00)
                .description("quality pillow with black cover")
                .category(categoryEntity1)
                .build();
        productEntity2 = productRepository.save(productEntity2);

        ProductEntity productEntity3 = ProductEntity.builder()
                .name("pillow3")
                .imageUrl("URL333")
                .price(190.12)
                .description("Распродажа, quality pillow with yellow cover")
                .category(categoryEntity1)
                .build();
        productEntity3 = productRepository.save(productEntity3);

        UserEntity userEntity1 = UserEntity.builder()
                .name("Misha")
                .email("aaa@gmail.com")
                .phoneNumber("123456789")
                .role(Role.CLIENT)
                .passwordHash("111222333")
                .build();
        userEntity1 = userRepository.save(userEntity1);

        UserEntity userEntity2 = UserEntity.builder()
                .name("Tim")
                .email("bbbbbbbbbbbb@gmail.com")
                .phoneNumber("987654321")
                .role(Role.ADMINISTRATOR)
                .passwordHash("9988777")
                .build();
        userEntity2 = userRepository.save(userEntity2);

        UserEntity userEntity3 = UserEntity.builder()
                .name("Tim")
                .email("ddddddddddd@gmail.com")
                .phoneNumber("7777777777")
                .role(Role.CLIENT)
                .passwordHash("46+*8468--5")
                .build();
        userEntity3 = userRepository.save(userEntity3);

        FavoriteEntity favoriteEntity1 = FavoriteEntity.builder()
                .user(userEntity1)
                .product(productEntity1)
                .build();
        favoriteEntity1 = favoriteRepository.save(favoriteEntity1);
    }
}
