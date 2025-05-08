package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryServiceList implements StorageServiceInterface<Category, Long>{

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

    @Override
    public Category getById(Long id) {
        for (Category category : categoryLocalStorage) {
            if (category.getCategoryId().equals(id))
                return category;
        }
        return null;
    }

    @Override
    public Category create(Category newCategory) {
        if (categoryLocalStorage.add(newCategory)) {
            return getById(newCategory.getCategoryId());
        }
        return null;
    }

    @Override
    public Category updateById(Long id, Category updateCategory) {
        for (int i = 0; i < categoryLocalStorage.size(); i++) {
            Category category = categoryLocalStorage.get(i);
            if (category.getCategoryId().equals(id)) {
                categoryLocalStorage.set(i, updateCategory);
                System.out.println("Проведено обновление Id: " + id);
                return categoryLocalStorage.get(i);
            }
        }
        System.out.println("При обновлении объект с Id " + id + " не был обнаружен, поэтому в базу внесен новый объект с таким Id");
        return create(updateCategory);
    }

    @Override
    public void deleteById(Long id) {
        if (getById(id) == null) {
            throw new IllegalArgumentException("Объекта с таким Id не существует");
        }
        for (int i = 0; i < categoryLocalStorage.size(); i++) {//удаление реализовано без итератора
            if (categoryLocalStorage.get(i).getCategoryId() == id) {
                categoryLocalStorage.remove(i);
            }
        }
    }
}
