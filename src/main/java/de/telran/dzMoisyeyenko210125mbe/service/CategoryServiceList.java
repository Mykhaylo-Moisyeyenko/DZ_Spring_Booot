package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryServiceList implements StorageServiceInterface<Category>{

    private List<Category> categoryLocalStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        Category category1 = new Category(1L, "Фрукты");
        Category category2 = new Category(2L, "Овощи");

        categoryLocalStorage.add(category1);
        categoryLocalStorage.add(category2);
    }

    @Override
    public List<Category> getAll() {
        return categoryLocalStorage;
    }
}
