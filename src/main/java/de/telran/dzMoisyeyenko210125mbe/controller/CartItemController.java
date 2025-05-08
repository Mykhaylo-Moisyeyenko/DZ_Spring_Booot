package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.CartItem;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartitem")
public class CartItemController {

    @Autowired
    private StorageServiceInterface<CartItem, Long> storageServiceInterface;

    @GetMapping
    public List<CartItem> getAll() {
        System.out.println("Привет, я GET-запрос контроллера - CartItemController для получения всех CartItem");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{id}")
    public CartItem getById(@PathVariable Long id) {
        System.out.println("Привет, я GET-запрос контроллера - CartItemController для получения объекта по Id");
        return storageServiceInterface.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartItem create(@RequestBody CartItem cartItem){
        System.out.println("Привет, я POST-запрос контроллера - CartItemController");
        System.out.println("В POST-запросе поступило: " + cartItem);
        return storageServiceInterface.create(cartItem);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CartItem updateById(@PathVariable Long id, @RequestBody CartItem updateCartItem){
        System.out.println("Привет, я PUT-запрос контроллера - CartItemController");
        return storageServiceInterface.updateById(id, updateCartItem);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        System.out.println("Привет, я DELETE-запрос контроллера - CartItemController");
        storageServiceInterface.deleteById(id);
    }
}
