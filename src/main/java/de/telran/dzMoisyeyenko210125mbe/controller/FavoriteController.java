package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.Favorite;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {

    @Autowired
    private StorageServiceInterface<Favorite, Long> storageServiceInterface;

    @GetMapping
    public List<Favorite> getAll(){
        System.out.println("Привет, я GET-запрос контроллера - FavoriteController для получения всех объектов");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{id}")
    public Favorite getById(@PathVariable Long id) {
        System.out.println("Привет, я GET-запрос контроллера - FavoriteController для получения объекта по Id");
        return storageServiceInterface.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Favorite create(@RequestBody Favorite favorite){
        System.out.println("Привет, я POST-запрос контроллера - FavoriteController");
        System.out.println("В POST-запросе поступило: " + favorite);
        return storageServiceInterface.create(favorite);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Favorite updateById(@PathVariable Long id, @RequestBody Favorite updateFavorite){
        System.out.println("Привет, я PUT-запрос контроллера - FavoriteController");
        return storageServiceInterface.updateById(id, updateFavorite);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        System.out.println("Привет, я DELETE-запрос контроллера - FavoriteController");
        storageServiceInterface.deleteById(id);
    }
}
