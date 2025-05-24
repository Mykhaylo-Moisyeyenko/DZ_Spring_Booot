package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.Cart;
import de.telran.dzMoisyeyenko210125mbe.pojo.Role;
import de.telran.dzMoisyeyenko210125mbe.pojo.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class CartServiceList implements StorageServiceInterface<Cart, Long> {

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

    @Override
    public Cart getById(Long id) throws Exception {
        for (Cart cart : cartLocalStorage) {
            if (cart.getCartId().equals(id))
                return cart;
        }
        return null;
    }

    @Override
    public Cart create(Cart newCart) {
        if (cartLocalStorage.add(newCart)) {
            try {
                return getById(newCart.getCartId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public Cart updateById(Long id, Cart updateCart) {
        for (int i = 0; i < cartLocalStorage.size(); i++) {
            Cart cart = cartLocalStorage.get(i);
            if (cart.getCartId().equals(id)) {
                cartLocalStorage.set(i, updateCart);
                System.out.println("Проведено обновление Id: " + id);
                return cartLocalStorage.get(i);
            }
        }
        System.out.println("При обновлении объект с Id " + id + " не был обнаружен, поэтому в базу внесен новый объект с таким Id");
        return create(updateCart);
    }

    @Override
    public void deleteById(Long id) {
        try {
            if (getById(id) == null) {
                throw new IllegalArgumentException("Объекта с таким Id не существует");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < cartLocalStorage.size(); i++) {//удаление реализовано без итератора
            if (cartLocalStorage.get(i).getCartId() == id) {
                cartLocalStorage.remove(i);
            }
        }
    }

    @Override //обновляю только поле User
    public Cart updatePart(Long id, Cart updatePart) throws Exception {
        for (Cart updatedPart : cartLocalStorage) {
            if(updatedPart.getCartId().equals(id)) {
                if (!updatedPart.getUser().equals(updatePart.getUser()))
                    updatedPart.setUser(updatePart.getUser());
                return updatedPart;
            }
        }
        throw new NoSuchElementException("При update не нашли объект с id = "+id);
    }

    @Override
    public Cart getByEmail(String valueEmail) {
        return null;
    }

    @Override
    public List<Cart> getByName(String valueName) {
        return List.of();
    }
}
