package de.telran.dzMoisyeyenko210125mbe.jdbc;

import de.telran.dzMoisyeyenko210125mbe.pojo.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        final long id = rs.getInt("CategoryID");
        final String name = rs.getString("Name");
        return new Category(id, name);
    }
}
