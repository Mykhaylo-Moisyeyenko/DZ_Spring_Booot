package de.telran.dzMoisyeyenko210125mbe.controller;

import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import de.telran.dzMoisyeyenko210125mbe.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductServiceInterface serviceProductList;

    @GetMapping
    public List<Product> getAllproducts() {
        System.out.println("Получаем информацию о всех продуктах");
        return serviceProductList.getAllProducts();
    }

    @GetMapping("/find/{id}")
    public Product getProductById(@PathVariable Long id) {
        System.out.println("Получаем информацию о товаре c Id: " + id);
        return serviceProductList.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product insertProduct(@RequestBody Product newProduct) {
        System.out.println("Внесли в базу новый товар с Id: " + newProduct.getProductId());
        return serviceProductList.insertProduct(newProduct);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updateProduct) {
        return serviceProductList.updateProduct(id, updateProduct);
    }

    @PatchMapping("/{id}")
    public Product updatePartProduct(@PathVariable Long id, @RequestBody Product updateProduct){
        return serviceProductList.updatePartProduct(id, updateProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        System.out.println("Удалён товар с id " + id);
        serviceProductList.deleteProductById(id);
    }
}
