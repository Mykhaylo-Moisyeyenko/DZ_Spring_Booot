package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.OrderItem;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderitem")
public class OrderItemController {

    @Autowired
    private StorageServiceInterface<OrderItem, Long> storageServiceInterface;

    @GetMapping
    public List<OrderItem> getAll(){
        System.out.println("Привет, я GET-запрос контроллера - OrderItemController для получения всех OrderItem");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{id}")
    public OrderItem getById(@PathVariable Long id) {
        System.out.println("Привет, я GET-запрос контроллера - OrderItemController для получения объекта по Id");
        try {
            return storageServiceInterface.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderItem create(@RequestBody OrderItem orderItem){
        System.out.println("Привет, я POST-запрос контроллера - OrderItemController");
        System.out.println("В POST-запросе поступило: " + orderItem);
        return storageServiceInterface.create(orderItem);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderItem updateById(@PathVariable Long id, @RequestBody OrderItem orderItem){
        System.out.println("Привет, я PUT-запрос контроллера - OrderItemController");
        return storageServiceInterface.updateById(id, orderItem);
    }

    // обновление части информации, если объекта не существует, новый не создаем
    @PatchMapping("/{id}")
    public ResponseEntity<OrderItem> updatePart(@PathVariable Long id, @RequestBody OrderItem updatePart) throws Exception{
        System.out.println("Привет, я PATCH-запрос контроллера - OrderItemController");
        System.out.println("Произошло редактирование части информации объекта");
        OrderItem updatedPart = storageServiceInterface.updatePart(id, updatePart);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedPart);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws Exception {
        System.out.println("Привет, я DELETE-запрос контроллера - OrderItemController");
        storageServiceInterface.deleteById(id);
    }
}
