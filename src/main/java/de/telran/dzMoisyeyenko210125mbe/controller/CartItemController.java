package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.Cart;
import de.telran.dzMoisyeyenko210125mbe.pojo.CartItem;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartitem")
public class CartItemController {

    @Autowired
    private StorageServiceInterface<CartItem> storageServiceInterface;

    @GetMapping
    public List<CartItem> getAll() {
        System.out.println("Привет, я GET-запрос контроллера - CartItemController для получения всех CartItem");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{Id}")
    public void getById(@PathVariable Long Id) {
        System.out.println("Привет, я GET-запрос контроллера - CartItemController для получения CartItem по Id");
    }

    @PostMapping
    public void create(@RequestBody CartItem cartItem){
        System.out.println("Привет, я POST-запрос контроллера - CartItemController");
        System.out.println("В POST-запросе поступило: " + cartItem);
    }

    @PutMapping("/{Id}")
    public void updateById(@PathVariable Long Id){
        System.out.println("Привет, я PUT-запрос контроллера - CartItemController");
    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        System.out.println("Привет, я DELETE-запрос контроллера - CartItemController");
    }
}
