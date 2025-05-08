package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceList implements StorageServiceInterface<Product, Long> {

    private List<Product> localStorage = new ArrayList<>();

    @PostConstruct
    void init() {
        Product product1 = new Product();
        product1.setProductId(2L);
        product1.setName("Огурец");
        product1.setPrice(1.4);

        Product product2 = new Product();
        product2.setProductId(3L);
        product2.setName("Помидор");
        product2.setPrice(2.8);

        localStorage.add(product1);
        localStorage.add(product2);
    }

    @Override
    public List<Product> getAll() {
        return localStorage;
    }

    @Override
    public Product getById(Long productId) {
        for (Product product : localStorage) {
            if (product.getProductId().equals(productId))
                return product;
        }
        return null;
    }

    @Override
    public Product create(Product newProduct) {
        if (localStorage.add(newProduct)) {
            return getById(newProduct.getProductId());
        }
        return null;
    }

    @Override
    public Product updateById(Long id, Product updateProduct) {
        for (int i = 0; i < localStorage.size(); i++) {
            Product product = localStorage.get(i);
            if (product.getProductId().equals(id)) {
                localStorage.set(i, updateProduct);
                System.out.println("Проведено обновление продукта c Id: " + id);
                return localStorage.get(i);
            }
        }
        System.out.println("При обновлении продукта, продукт с Id " + id + " не был обнаружен, поэтому в базу внесен новый продукт с таким Id");
        return create(updateProduct);
    }

//    @Override
//    public Product updatePartProduct(Long id, Product updateProduct) {
//        for (Product product : localStorage) {
//            if (product.getProductId().equals(id)) {
//                if (!product.getName().equals(updateProduct.getName())) {
//                    product.setName(updateProduct.getName());
//                }
//                if (product.getDescription() == null
//                        || !product.getDescription().equals(updateProduct.getDescription())) {
//                    product.setDescription(updateProduct.getDescription());
//                }
//                if (product.getPrice() == null
//                        || !product.getPrice().equals(updateProduct.getPrice())) {
//                    product.setPrice(updateProduct.getPrice());
//                }
//                System.out.println("Отредактирован продукт с id: " + id);
//                return product;
//            }
//        }
//        System.out.println("Продукт с Id " + id + " не найден");
//        return null;
//    }

    @Override
    public void deleteById(Long id) {
        if (getById(id) == null) {
            throw new IllegalArgumentException("Продукта с таким Id не существует");
        }
        for (int i = 0; i < localStorage.size(); i++) {//удаление реализовано без итератора
            if (localStorage.get(i).getProductId() == id) {
                localStorage.remove(i);
            }
        }
    }
}

