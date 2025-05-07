package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.Order;
import de.telran.dzMoisyeyenko210125mbe.pojo.OrderItem;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderitem")
public class OrderItemController {

    @Autowired
    private StorageServiceInterface<OrderItem> storageServiceInterface;

    @GetMapping
    public List<OrderItem> getAll(){
        System.out.println("Привет, я GET-запрос контроллера - OrderItemController для получения всех OrderItem");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{Id}")
    public void getById(@PathVariable Long Id) {
        System.out.println("Привет, я GET-запрос контроллера - OrderItemController");
    }

    @PostMapping
    public void create(@RequestBody OrderItem orderItem){
        System.out.println("Привет, я POST-запрос контроллера - OrderItemController");
        System.out.println("В POST-запросе поступило: " + orderItem);
    }

    @PutMapping("/{Id}")
    public void updateById(@PathVariable Long Id){
        System.out.println("Привет, я PUT-запрос контроллера - OrderItemController");
    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        System.out.println("Привет, я DELETE-запрос контроллера - OrderItemController");
    }
}
