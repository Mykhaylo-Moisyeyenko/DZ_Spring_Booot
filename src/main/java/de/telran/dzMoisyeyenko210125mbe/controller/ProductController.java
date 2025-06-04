package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.exception.BadRequestException;
import de.telran.dzMoisyeyenko210125mbe.model.dto.ProductDto;
import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import de.telran.dzMoisyeyenko210125mbe.service.ProductServiceList;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceList productServiceList;

    private final StorageServiceInterface<ProductDto, Long> storageServiceInterface;

    @GetMapping("/name/{valueName}")
    public List<ProductDto> getByName(@PathVariable String valueName) {
        return storageServiceInterface.getByName(valueName);
    }

    @GetMapping("/discount")
    public List<ProductDto> getByDiscount() {
        return productServiceList.findByDiscount();
    }

    //1. Напишите методы в Репозитории для редактирования цены товара и дисконта в таблице Product c использованием @Query.
    @PatchMapping("/changeprices/{id}")//Запрос: localhost:8088/api/product/changeprices/1?newPrice=15.00&newDiscountPrice=12.00
    public ProductDto updatePriceAndDiscountOfProduct(@PathVariable Long id, @RequestParam(name = "newPrice") Double newPrice, @RequestParam(name = "newDiscountPrice") Double newDiscountPrice){
        return productServiceList.updatePriceAndDiscountOfProduct(id, newPrice, newDiscountPrice);
    }

    //    1) * напишите метод, позволяющий найти продукты, в описании которых (Description)
    //    есть слово "Распродажа" и цена которых более 100 евро.
    @GetMapping("/getBySaleAndPrice") //запрос: localhost:8088/api/product/getBySaleAndPrice?description=Распродажа&price=10
    public List<ProductDto> getByDescriptionContainingAndPriceGreaterThan(@RequestParam String description, @RequestParam Double price){
        return productServiceList.getByDescriptionContainingAndPriceGreaterThan(description, price);
    }

    //    2) ** напишите метод, позволяющий найти продукты, категорию которых мы можем задать через
//    первый аргумент метода и которые имеют дисконтную цену (DiscountPrice > 0).
    @GetMapping("/getByCategoryAndDiscountPrice") //запрос: localhost:8088/api/product/getByCategoryAndDiscountPrice?categoryName=Pillows&discountPrice=0
    public List<ProductDto> getByCategoryNameAndDiscountPriceGreaterThan(@RequestParam String categoryName, @RequestParam Double discountPrice){
        return productServiceList.getByCategoryNameAndDiscountPriceGreaterThan(categoryName, discountPrice);
    }




    // ниже - методы для запросов для ДЗ до 23.05.2025:
    @GetMapping
    public List<ProductDto> getAll() {
        System.out.println("Привет, я GET-запрос контроллера - ProductController для получения всех объектов");
        return storageServiceInterface.getAll();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) throws Exception {
        System.out.println("Привет, я GET-запрос контроллера - ProductController для получения объекта по Id");
        try {
            return storageServiceInterface.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody ProductDto newProductDto) {
        System.out.println("Привет, я POST-запрос контроллера - ProductController");
        System.out.println("В POST-запросе поступило: " + newProductDto);
        return storageServiceInterface.create(newProductDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public ProductDto updateById(@PathVariable Long id, @RequestBody ProductDto updateProduct) {
        System.out.println("Привет, я PUT-запрос контроллера - ProductController");
        return storageServiceInterface.updateById(id, updateProduct);
    }

    // обновление части информации, если объекта не существует, новый не создаем
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> updatePart(@PathVariable Long id, @RequestBody ProductDto updatePart) throws Exception {
        System.out.println("Привет, я PATCH-запрос контроллера - ProductController");
        System.out.println("Произошло редактирование части информации объекта");
        ProductDto updatedPart = storageServiceInterface.updatePart(id, updatePart);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedPart);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws Exception {
        System.out.println("Привет, я DELETE-запрос контроллера - ProductController");
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
        return "Controller(Exception): Извините, произошла ошибка, попробуйте позже выполнить операцию. "
                + exception.getMessage();
    }
}
