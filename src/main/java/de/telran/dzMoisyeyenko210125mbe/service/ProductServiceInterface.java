package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.Product;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> getAllProducts();

    Product getProductById(Long productId);

    Product insertProduct(Product newProduct);

    Product updateProduct(Long productId, Product updateProduct);

    void deleteProductById(Long id);

    Product updatePartProduct(Long id, Product updateProduct);
}
