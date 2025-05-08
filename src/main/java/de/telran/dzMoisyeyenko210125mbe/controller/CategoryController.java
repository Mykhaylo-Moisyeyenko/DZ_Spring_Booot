package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.CartItem;
import de.telran.dzMoisyeyenko210125mbe.pojo.Category;
import de.telran.dzMoisyeyenko210125mbe.pojo.User;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private StorageServiceInterface<Category, Long> storageServiceInterface;

    @GetMapping
    public List<Category> getAll() {
        System.out.println("Привет, я GET-запрос контроллера - CategoryController для получения всех Category");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        System.out.println("Привет, я GET-запрос контроллера - CategoryController для получения объекта по Id");
        return storageServiceInterface.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody Category category){
        System.out.println("Привет, я POST-запрос контроллера - CategoryController");
        System.out.println("В POST-запросе поступило: " + category);
        return storageServiceInterface.create(category);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Category updateById(@PathVariable Long id, @RequestBody Category updateCategory){
        System.out.println("Привет, я PUT-запрос контроллера - CategoryController");
        return storageServiceInterface.updateById(id, updateCategory);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        System.out.println("Привет, я DELETE-запрос контроллера - CategoryController");
        storageServiceInterface.deleteById(id);
    }
}
