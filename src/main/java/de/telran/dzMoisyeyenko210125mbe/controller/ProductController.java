package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private StorageServiceInterface<Product, Long> storageServiceInterface;

    @GetMapping
    public List<Product> getAll() {
        System.out.println("Привет, я GET-запрос контроллера - ProductController для получения всех объектов");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        System.out.println("Привет, я GET-запрос контроллера - ProductController для получения объекта по Id");
        return storageServiceInterface.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product newProduct) {
        System.out.println("Привет, я POST-запрос контроллера - ProductController");
        System.out.println("В POST-запросе поступило: " + newProduct);
        return storageServiceInterface.create(newProduct);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Product updateById(@PathVariable Long id, @RequestBody Product updateProduct) {
        System.out.println("Привет, я PUT-запрос контроллера - ProductController");
        return storageServiceInterface.updateById(id, updateProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        System.out.println("Привет, я DELETE-запрос контроллера - ProductController");
        storageServiceInterface.deleteById(id);
    }
}
