package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.Cart;
import de.telran.dzMoisyeyenko210125mbe.pojo.Role;
import de.telran.dzMoisyeyenko210125mbe.pojo.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartServiceList implements StorageServiceInterface<Cart> {

    private List<Cart> cartLocalStorage = new ArrayList<>();

    @PostConstruct
    void init() {
        User user1 = new User(1L, "Misha", "123@gmail.com", "3-44-22", "sdfs", Role.CLIENT);
        Cart cart1 = new Cart(1L, user1);

        cartLocalStorage.add(cart1);
    }

    @Override
    public List<Cart> getAll() {
        return cartLocalStorage;
    }
}
