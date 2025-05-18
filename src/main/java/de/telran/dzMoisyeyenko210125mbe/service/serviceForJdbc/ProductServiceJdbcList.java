package de.telran.dzMoisyeyenko210125mbe.service.serviceForJdbc;

import de.telran.dzMoisyeyenko210125mbe.jdbc.ProductDbInterface;
import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary //пока пометил этот сервис как Primary, только для целей ДЗ по JDBC
public class ProductServiceJdbcList implements StorageServiceInterface<Product, Long> {

    @Autowired
    ProductDbInterface productDbInterface;

    @Override
    public Product getById(Long id) throws Exception {
        return productDbInterface.findById(id);
    }

    @Override
    public List<Product> getAll() {
        return productDbInterface.findAll();
    }


    // Нижеследующие методы пока реализованы как заглушки, просто чтобы Идеа не ругалась:

    @Override
    public Product create(Product entity) {
        return null;
    }

    @Override
    public Product updateById(Long aLong, Product entity) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws Exception {

    }

    @Override
    public Product updatePart(Long aLong, Product entity) throws Exception {
        return null;
    }
}