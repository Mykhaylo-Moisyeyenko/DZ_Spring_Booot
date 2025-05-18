package de.telran.dzMoisyeyenko210125mbe.service.serviceForJdbc;

import de.telran.dzMoisyeyenko210125mbe.jdbc.CategoryDbInterface;
import de.telran.dzMoisyeyenko210125mbe.pojo.Category;
import de.telran.dzMoisyeyenko210125mbe.service.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary //пока пометил этот сервис как Primary, только для целей ДЗ по JDBC
public class CategoryServiceJdbcList implements StorageServiceInterface<Category, Long> {

    @Autowired
    CategoryDbInterface categoryDbInterface;

    @Override
    public Category getById(Long id) throws Exception {
        return categoryDbInterface.findById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryDbInterface.findAll();
    }


    // Нижеследующие методы пока реализованы как заглушки, просто чтобы Идеа не ругалась:

    @Override
    public Category create(Category entity) {
        return null;
    }

    @Override
    public Category updateById(Long aLong, Category entity) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
    }

    @Override
    public Category updatePart(Long aLong, Category entity) throws Exception {
        return null;
    }
}
