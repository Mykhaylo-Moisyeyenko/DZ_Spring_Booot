package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.model.dto.UserDto;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final StorageServiceInterface<UserDto, Long> storageServiceInterface;

    @GetMapping
    public List<UserDto> getAll(){
        System.out.println("Привет, я GET-запрос контроллера - UserController для получения всех User");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) throws Exception{
        System.out.println("Привет, я GET-запрос контроллера - ProductController для получения объекта по Id");
        try {
            return storageServiceInterface.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto userDto){
        System.out.println("Привет, я POST-запрос контроллера - UserController");
        System.out.println("В POST-запросе поступило: " + userDto);
        return storageServiceInterface.create(userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws Exception {
        System.out.println("Привет, я DELETE-запрос контроллера - UserController");
        storageServiceInterface.deleteById(id);
    }







    //Ниже - реализации методов CRUD под старые ДЗ:
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public User updateById(@PathVariable Long id, @RequestBody User updateUser){
//        System.out.println("Привет, я PUT-запрос контроллера - UserController");
//        return storageServiceInterface.updateById(id, updateUser);
//    }
//
//    // обновление части информации, если объекта не существует, новый не создаем
//    @PatchMapping("/{id}")
//    public ResponseEntity<User> updatePart(@PathVariable Long id, @RequestBody User updatePart) throws Exception{
//        System.out.println("Привет, я PATCH-запрос контроллера - UserController");
//        System.out.println("Произошло редактирование части информации объекта");
//        User updatedPart = storageServiceInterface.updatePart(id, updatePart);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedPart);
//    }

//
//    @ExceptionHandler(BadRequestException.class)//обработчик для пользовательского исключения BadRequestException
//    public ResponseEntity<String> handleBadRequestException(BadRequestException exception) {
//        return ResponseEntity.status(HttpStatus.valueOf(404))
//                .body("Controller: " + exception.getMessage());
//    }
//
//    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
//    @ExceptionHandler(Exception.class) // обработчик для всех остальных типов исключений
//    public String handleException(Exception exception) {
//        return  "Controller(Exception): Извините, произошла ошибка, попробуйте позже выполнить операцию. "
//                +exception.getMessage();
//    }
}
