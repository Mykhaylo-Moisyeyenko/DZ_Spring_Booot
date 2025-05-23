package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.model.dto.FavoriteDto;
import de.telran.dzMoisyeyenko210125mbe.pojo.Favorite;
import de.telran.dzMoisyeyenko210125mbe.repository.FavoriteRepository;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final StorageServiceInterface<FavoriteDto, Long> storageServiceInterface;

    @GetMapping
    public List<FavoriteDto> getAll(){
        System.out.println("Привет, я GET-запрос контроллера - FavoriteController для получения всех объектов");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{id}")
    public FavoriteDto getById(@PathVariable Long id) {
        System.out.println("Привет, я GET-запрос контроллера - FavoriteController для получения объекта по Id");
        try {
            return storageServiceInterface.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FavoriteDto create(@RequestBody FavoriteDto favorite){
        System.out.println("Привет, я POST-запрос контроллера - FavoriteController");
        System.out.println("В POST-запросе поступило: " + favorite);
        return storageServiceInterface.create(favorite);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws Exception {
        System.out.println("Привет, я DELETE-запрос контроллера - FavoriteController");
        storageServiceInterface.deleteById(id);
    }
}
