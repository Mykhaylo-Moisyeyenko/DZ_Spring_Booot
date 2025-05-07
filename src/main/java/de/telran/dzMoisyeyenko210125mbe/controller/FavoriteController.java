package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.Category;
import de.telran.dzMoisyeyenko210125mbe.pojo.Favorite;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {

    @Autowired
    private StorageServiceInterface<Favorite> storageServiceInterface;

    @GetMapping
    public List<Favorite> getAll(){
        System.out.println("Привет, я GET-запрос контроллера - FavoriteController для получения всех Category");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{Id}")
    public void getById(@PathVariable Long Id) {
        System.out.println("Привет, я GET-запрос контроллера - FavoriteController");
    }

    @PostMapping
    public void create(@RequestBody Favorite favorite){
        System.out.println("Привет, я POST-запрос контроллера - FavoriteController");
        System.out.println("В POST-запросе поступило: " + favorite);
    }

    @PutMapping("/{Id}")
    public void updateById(@PathVariable Long Id){
        System.out.println("Привет, я PUT-запрос контроллера - FavoriteController");
    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        System.out.println("Привет, я DELETE-запрос контроллера - FavoriteController");
    }
}
