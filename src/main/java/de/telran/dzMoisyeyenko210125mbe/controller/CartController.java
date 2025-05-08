package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.Cart;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cart")
@RestController
public class CartController {

    @Autowired
    private StorageServiceInterface<Cart, Long> storageServiceInterface;

    @GetMapping
    public List<Cart> getAll() {
        System.out.println("Привет, я GET-запрос контроллера - CartController для получения всех существующих корзин");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{id}")
    public Cart getById(@PathVariable Long id) {
        System.out.println("Привет, я GET-запрос контроллера - CartController для получения объекта по Id");
        return storageServiceInterface.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cart create(@RequestBody Cart cart){
        System.out.println("Привет, я POST-запрос контроллера - CartController");
        System.out.println("В POST-запросе поступило: " + cart);
        return storageServiceInterface.create(cart);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cart updateById(@PathVariable Long id, @RequestBody Cart updateCart){
        System.out.println("Привет, я PUT-запрос контроллера - CartController");
        return storageServiceInterface.updateById(id, updateCart);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        System.out.println("Привет, я DELETE-запрос контроллера - CartController");
        storageServiceInterface.deleteById(id);
    }
}
