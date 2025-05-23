package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.exception.BadRequestException;
import de.telran.dzMoisyeyenko210125mbe.model.dto.CategoryDto;
import de.telran.dzMoisyeyenko210125mbe.model.entity.CategoryEntity;
import de.telran.dzMoisyeyenko210125mbe.pojo.Category;
import de.telran.dzMoisyeyenko210125mbe.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceList implements StorageServiceInterface<Category, Long>{

    private final CategoryRepository categoryRepository;

    private List<Category> categoryLocalStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        //инициализация всех бинов - вынесена в класс DataInitializer

        //Ниже - инициализация продуктов для старых ДЗ:
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
    public Category getById(Long id) throws Exception {
        for (Category category : categoryLocalStorage) {
            if (category.getCategoryId().equals(id))
                return category;
        }
        throw new BadRequestException("Объект c Id= " + id + " не найден!!!");
    }

    @Override
    public Category create(Category newCategory) {
        if (categoryLocalStorage.add(newCategory)) {
            try {
                return getById(newCategory.getCategoryId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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

    @Override //обновляю только поле Name
    public Category updatePart(Long id, Category updatePart) throws Exception {
        for (Category updatedPart : categoryLocalStorage) {
            if(updatedPart.getCategoryId().equals(id)) {
                if (!updatedPart.getName().equals(updatePart.getName()))
                    updatedPart.setName(updatePart.getName());
                return updatedPart;
            }
        }
        throw new BadRequestException("Объект c Id= " + id + " не найден!!!");
    }

    @Override
    public Category getByEmail(String valueEmail) {
        return null;
    }

    @Override
    public List<Category> getByName(String valueName) {
        return List.of();
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if (getById(id) == null) {
            throw new BadRequestException("Объект c Id= " + id + " не найден!!!");
        }
        for (int i = 0; i < categoryLocalStorage.size(); i++) {//удаление реализовано без итератора
            if (categoryLocalStorage.get(i).getCategoryId() == id) {
                categoryLocalStorage.remove(i);
            }
        }
    }
}
