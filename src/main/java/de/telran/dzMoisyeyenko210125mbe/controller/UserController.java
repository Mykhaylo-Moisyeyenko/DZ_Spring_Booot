package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.CartItem;
import de.telran.dzMoisyeyenko210125mbe.pojo.OrderItem;
import de.telran.dzMoisyeyenko210125mbe.pojo.User;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private StorageServiceInterface<User, Long> storageServiceInterface;

    @GetMapping
    public List<User> getAll(){
        System.out.println("Привет, я GET-запрос контроллера - UserController для получения всех User");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        System.out.println("Привет, я GET-запрос контроллера - UserController для получения объекта по Id");
        return storageServiceInterface.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        System.out.println("Привет, я POST-запрос контроллера - UserController");
        System.out.println("В POST-запросе поступило: " + user);
        return storageServiceInterface.create(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateById(@PathVariable Long id, @RequestBody User updateUser){
        System.out.println("Привет, я PUT-запрос контроллера - UserController");
        return storageServiceInterface.updateById(id, updateUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        System.out.println("Привет, я DELETE-запрос контроллера - UserController");
        storageServiceInterface.deleteById(id);
    }
}
