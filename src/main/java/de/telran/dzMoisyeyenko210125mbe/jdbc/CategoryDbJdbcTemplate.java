package de.telran.dzMoisyeyenko210125mbe.jdbc;

import de.telran.dzMoisyeyenko210125mbe.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryDbJdbcTemplate implements CategoryDbInterface{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category findById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("ID", id);
        String sql ="select CategoryID, Name from Categories where CategoryID = :ID";
        Category result = jdbcTemplate.queryForObject(sql, params, categoryMapper);
        return result;
    }

    @Override
    public List<Category> findAll(){
        String sql = "select * from Categories";
        List<Category> result = jdbcTemplate.query(sql, new HashMap<>(), categoryMapper);
        return result;
    }
}
