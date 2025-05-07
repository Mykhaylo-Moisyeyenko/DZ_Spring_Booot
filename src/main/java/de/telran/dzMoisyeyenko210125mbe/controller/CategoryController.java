package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.CartItem;
import de.telran.dzMoisyeyenko210125mbe.pojo.Category;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private StorageServiceInterface<Category> storageServiceInterface;

    @GetMapping
    public List<Category> getAll() {
        System.out.println("Привет, я GET-запрос контроллера - CategoryController для получения всех Category");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{Id}")
    public void getById(@PathVariable Long Id) {
        System.out.println("Привет, я GET-запрос контроллера - CategoryController для получения CartItem по Id");
    }

    @PostMapping
    public void create(@RequestBody Category category){
        System.out.println("Привет, я POST-запрос контроллера - CategoryController");
        System.out.println("В POST-запросе поступило: " + category);
    }

    @PutMapping("/{Id}")
    public void updateById(@PathVariable Long Id){
        System.out.println("Привет, я PUT-запрос контроллера - CategoryController");
    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        System.out.println("Привет, я DELETE-запрос контроллера - CategoryController");
    }
}
