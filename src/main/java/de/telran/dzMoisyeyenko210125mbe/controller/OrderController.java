package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.Order;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private StorageServiceInterface<Order, Long> storageServiceInterface;

    @GetMapping
    public ResponseEntity <List<Order>> getAll(){
        List<Order> allOrders = storageServiceInterface.getAll();
        System.out.println("Привет, я GET-запрос контроллера - OrderController для получения всех Order");
//        ResponseEntity<List<Order>> responseEntity = new ResponseEntity<>(allOrders, HttpStatus.OK);
        ResponseEntity<List<Order>> responseEntity = new ResponseEntity<>(allOrders, HttpStatusCode.valueOf(200));
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Order gettedOrder = null;
        try {
            gettedOrder = storageServiceInterface.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Привет, я GET-запрос контроллера - OrderController для получения объекта по Id");
        if (gettedOrder==null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        } else {
//            return ResponseEntity.ok(gettedOrder);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
        }
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order){
        Order newOrder = storageServiceInterface.create(order);
        System.out.println("Привет, я POST-запрос контроллера - OrderController");
        System.out.println("В POST-запросе поступило: " + order);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
        return new ResponseEntity<>(newOrder, HttpStatusCode.valueOf(201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateById(@PathVariable Long id, @RequestBody Order updateOrder){
        Order updatedOrder = storageServiceInterface.updateById(id, updateOrder);
        System.out.println("Привет, я PUT-запрос контроллера - OrderController");
        return new ResponseEntity<>(updatedOrder, HttpStatus.ACCEPTED);
    }

    // обновление части информации, если объекта не существует, новый не создаем
    @PatchMapping("/{id}")
    public ResponseEntity<Order> updatePart(@PathVariable Long id, @RequestBody Order updatePart) throws Exception{
        System.out.println("Привет, я PATCH-запрос контроллера - OrderController");
        System.out.println("Произошло редактирование части информации объекта");
        Order updatedPart = storageServiceInterface.updatePart(id, updatePart);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedPart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws Exception {
        System.out.println("Привет, я DELETE-запрос контроллера - OrderController");
        storageServiceInterface.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
