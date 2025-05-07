package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.Cart;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cart")
@RestController
public class CartController {

    @Autowired
    private StorageServiceInterface<Cart> storageServiceInterface;

    @GetMapping
    public List<Cart> getAllCarts() {
        System.out.println("Привет, я GET-запрос контроллера - CartController для получения всех существующих корзин");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{userId}")
    public void getCartByUserId(@PathVariable Long userId) {
        System.out.println("Привет, я GET-запрос контроллера - CartController для получения корзины по userId");
    }

    @PostMapping
    public void createCartForUser(@RequestBody Cart cart){
        System.out.println("Привет, я POST-запрос контроллера - CartController");
        System.out.println("В POST-запросе поступило: " + cart);
    }

    @PutMapping("/{userId}")
    public void updateCartByUserId(@PathVariable Long userId){
        System.out.println("Привет, я PUT-запрос контроллера - CartController");
    }

    @DeleteMapping("/{userId}")
    public void deleteCartByUserId(@PathVariable Long userId){
        System.out.println("Привет, я DELETE-запрос контроллера - CartController");
    }
}
