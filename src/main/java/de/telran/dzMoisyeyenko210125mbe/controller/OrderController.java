package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.Favorite;
import de.telran.dzMoisyeyenko210125mbe.pojo.Order;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private StorageServiceInterface<Order> storageServiceInterface;

    @GetMapping
    public List<Order> getAll(){
        System.out.println("Привет, я GET-запрос контроллера - OrderController для получения всех Order");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{Id}")
    public void getById(@PathVariable Long Id) {
        System.out.println("Привет, я GET-запрос контроллера - OrderController для получения по Id");
    }

    @PostMapping
    public void create(@RequestBody Order order){
        System.out.println("Привет, я POST-запрос контроллера - OrderController");
        System.out.println("В POST-запросе поступило: " + order);
    }

    @PutMapping("/{Id}")
    public void updateById(@PathVariable Long Id){
        System.out.println("Привет, я PUT-запрос контроллера - OrderController");
    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        System.out.println("Привет, я DELETE-запрос контроллера - OrderController");
    }
}
