package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.exception.BadRequestException;
import de.telran.dzMoisyeyenko210125mbe.pojo.Category;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Category getById(@PathVariable Long id) throws Exception {
        System.out.println("Привет, я GET-запрос контроллера - CategoryController для получения объекта по Id");
        try {
            return storageServiceInterface.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    // обновление части информации, если объекта не существует, новый не создаем
    @PatchMapping("/{id}")
    public ResponseEntity<Category> updatePart(@PathVariable Long id, @RequestBody Category updatePart) throws Exception{
        System.out.println("Привет, я PATCH-запрос контроллера - CategoryController");
        System.out.println("Произошло редактирование части информации объекта");
        Category updatedPart = storageServiceInterface.updatePart(id, updatePart);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedPart);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws Exception {
        System.out.println("Привет, я DELETE-запрос контроллера - CategoryController");
        storageServiceInterface.deleteById(id);
    }

    @ExceptionHandler(BadRequestException.class)//обработчик для пользовательского исключения BadRequestException
    public ResponseEntity<String> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity.status(HttpStatus.valueOf(404))
                .body("Controller: " + exception.getMessage());
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(Exception.class) // обработчик для всех остальных типов исключений
    public String handleException(Exception exception) {
        return  "Controller(Exception): Извините, произошла ошибка, попробуйте позже выполнить операцию. "
                +exception.getMessage();
    }
}
