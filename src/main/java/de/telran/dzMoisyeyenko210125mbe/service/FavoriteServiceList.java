package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.Favorite;
import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import de.telran.dzMoisyeyenko210125mbe.pojo.Role;
import de.telran.dzMoisyeyenko210125mbe.pojo.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FavoriteServiceList implements StorageServiceInterface<Favorite>{

    private List<Favorite> favoriteLocalStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        Product product1 = new Product();
        product1.setProductId(2L);
        product1.setName("Огурец");
        product1.setPrice(1.4);

        User user3 = new User(1L, "Pasha", "1234@gmail.com", "3-44-22", "sdfs", Role.CLIENT);

        Favorite favorite1 = new Favorite(1L, user3, product1);

        favoriteLocalStorage.add(favorite1);
    }

    @Override
    public List<Favorite> getAll() {
        return favoriteLocalStorage;
    }
}
