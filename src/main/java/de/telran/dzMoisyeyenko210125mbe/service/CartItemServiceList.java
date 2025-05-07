package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemServiceList implements StorageServiceInterface<CartItem>{

    private final List<CartItem> cartItemLocalStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        User user2 = new User(1L, "Misha", "123@gmail.com", "3-44-22", "sdfs", Role.CLIENT);
        Cart cart2 = new Cart(1L, user2);
        Product product3 = new Product();
        product3.setProductId(4L);
        product3.setName("Буряк");
        product3.setPrice(0.8);
        CartItem cartItem1 = new CartItem(1L, cart2, product3, 10);
        cartItemLocalStorage.add(cartItem1);
    }

    @Override
    public List<CartItem> getAll() {
        return cartItemLocalStorage;
    }
}
