package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.Cart;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        try {
            return storageServiceInterface.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    // обновление части информации, если объекта не существует, новый не создаем
    @PatchMapping("/{id}")
    public ResponseEntity<Cart> updatePart(@PathVariable Long id, @RequestBody Cart updatePart) throws Exception{
        System.out.println("Привет, я PATCH-запрос контроллера - CartController");
        System.out.println("Произошло редактирование части информации объекта");
        Cart updatedPart = storageServiceInterface.updatePart(id, updatePart);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedPart);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws Exception {
        System.out.println("Привет, я DELETE-запрос контроллера - CartController");
        storageServiceInterface.deleteById(id);
    }
}
