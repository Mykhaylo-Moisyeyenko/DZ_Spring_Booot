package de.telran.dzMoisyeyenko210125mbe.jdbc;

import de.telran.dzMoisyeyenko210125mbe.pojo.Category;
import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class ProductMapper implements RowMapper<Product> {

    @Autowired
    CategoryDbInterface categoryDbInterface;//он нужен, чтобы получить объект Category по Id

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        final long productId = rs.getInt("ProductID");
        final String name = rs.getString("Name");
        final String description = rs.getString("Description");
        final Double price = rs.getDouble("Price");

        final Long categoryId = rs.getLong("CategoryID");
        Category category = categoryDbInterface.findById(categoryId);//получаю связанный объект Category по Id

        final String imageUrl = rs.getString("ImageURL");
        final Double discountPrice = rs.getDouble("DiscountPrice");
        final Timestamp createdAt = rs.getTimestamp("CreatedAt");
        final Timestamp updatedAt = rs.getTimestamp("UpdatedAt");

        return new Product(productId, name, description, price, category, imageUrl, discountPrice, createdAt, updatedAt);
    }
}