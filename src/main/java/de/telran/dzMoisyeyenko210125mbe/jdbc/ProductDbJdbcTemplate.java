package de.telran.dzMoisyeyenko210125mbe.jdbc;

import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDbJdbcTemplate implements ProductDbInterface{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired // пришлось добавить, чтобы Spring корректно его создавал как бин
    private ProductMapper productMapper;

    @Override
    public Product findById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("ID", id);//
        String sql ="select * from Products where ProductID = :ID";
        Product result = jdbcTemplate.queryForObject(sql, params, productMapper);
        return result;
    }

    @Override
    public List<Product> findAll(){
        String sql = "select * from Products";
        List<Product> result = jdbcTemplate.query(sql, new HashMap<>(), productMapper);
        return result;
    }
}
