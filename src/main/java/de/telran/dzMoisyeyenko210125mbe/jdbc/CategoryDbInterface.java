package de.telran.dzMoisyeyenko210125mbe.jdbc;

import de.telran.dzMoisyeyenko210125mbe.pojo.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryDbInterface {

    Category findById(long id);

    List<Category> findAll();

}


