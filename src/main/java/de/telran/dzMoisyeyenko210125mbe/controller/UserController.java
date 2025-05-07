package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.OrderItem;
import de.telran.dzMoisyeyenko210125mbe.pojo.User;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private StorageServiceInterface<User> storageServiceInterface;

    @GetMapping
    public List<User> getAll(){
        System.out.println("Привет, я GET-запрос контроллера - UserController для получения всех User");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{Id}")
    public void getById(@PathVariable Long Id) {
        System.out.println("Привет, я GET-запрос контроллера - UserController для получения User по Id");
    }

    @PostMapping
    public void create(@RequestBody User user){
        System.out.println("Привет, я POST-запрос контроллера - UserController");
        System.out.println("В POST-запросе поступило: " + user);
    }

    @PutMapping("/{Id}")
    public void updateById(@PathVariable Long Id){
        System.out.println("Привет, я PUT-запрос контроллера - UserController");
    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        System.out.println("Привет, я DELETE-запрос контроллера - UserController");
    }
}
