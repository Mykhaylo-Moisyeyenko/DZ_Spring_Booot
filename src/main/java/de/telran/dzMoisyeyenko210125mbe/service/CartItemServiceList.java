package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class CartItemServiceList implements StorageServiceInterface<CartItem, Long>{

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

    @Override
    public CartItem getById(Long id) throws Exception {
        for (CartItem cartItem : cartItemLocalStorage) {
            if (cartItem.getCartItemId().equals(id))
                return cartItem;
        }
        return null;
    }

    @Override
    public CartItem create(CartItem newCartItem) {
        if (cartItemLocalStorage.add(newCartItem)) {
            try {
                return getById(newCartItem.getCartItemId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public CartItem updateById(Long id, CartItem updateCartItem) {
        for (int i = 0; i < cartItemLocalStorage.size(); i++) {
            CartItem cartItem = cartItemLocalStorage.get(i);
            if (cartItem.getCartItemId().equals(id)) {
                cartItemLocalStorage.set(i, updateCartItem);
                System.out.println("Проведено обновление Id: " + id);
                return cartItemLocalStorage.get(i);
            }
        }
        System.out.println("При обновлении объект с Id " + id + " не был обнаружен, поэтому в базу внесен новый объект с таким Id");
        return create(updateCartItem);
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
        for (int i = 0; i < cartItemLocalStorage.size(); i++) {//удаление реализовано без итератора
            if (cartItemLocalStorage.get(i).getCartItemId() == id) {
                cartItemLocalStorage.remove(i);
            }
        }
    }

    @Override //обновляю только поле Product
    public CartItem updatePart(Long id, CartItem updatePart) throws Exception {
        for (CartItem updatedPart : cartItemLocalStorage) {
            if(updatedPart.getCartItemId().equals(id)) {
                if (!updatedPart.getProduct().equals(updatePart.getProduct()))
                    updatedPart.setProduct(updatePart.getProduct());
                return updatedPart;
            }
        }
        throw new NoSuchElementException("При update не нашли объект с id = "+id);
    }

    @Override
    public CartItem getByEmail(String valueEmail) {
        return null;
    }

    @Override
    public List<CartItem> getByName(String valueName) {
        return List.of();
    }
}
