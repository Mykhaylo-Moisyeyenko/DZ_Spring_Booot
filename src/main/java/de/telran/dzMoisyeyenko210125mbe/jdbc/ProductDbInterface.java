package de.telran.dzMoisyeyenko210125mbe.jdbc;

import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ProductDbInterface {

    Product findById(long id);

    List<Product> findAll();

}