package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.Order;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private StorageServiceInterface<Order, Long> storageServiceInterface;

    @GetMapping
    public List<Order> getAll(){
        System.out.println("Привет, я GET-запрос контроллера - OrderController для получения всех Order");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        System.out.println("Привет, я GET-запрос контроллера - OrderController для получения объекта по Id");
        return storageServiceInterface.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order){
        System.out.println("Привет, я POST-запрос контроллера - OrderController");
        System.out.println("В POST-запросе поступило: " + order);
        return storageServiceInterface.create(order);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Order updateById(@PathVariable Long id, @RequestBody Order updateOrder){
        System.out.println("Привет, я PUT-запрос контроллера - OrderController");
        return storageServiceInterface.updateById(id, updateOrder);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        System.out.println("Привет, я DELETE-запрос контроллера - OrderController");
        storageServiceInterface.deleteById(id);
    }
}
