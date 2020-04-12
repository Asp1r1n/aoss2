package aoss.assignment.restservice.repos.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Shrub;
import aoss.assignment.restservice.models.inventory.mappers.SeedRowMapper;
import aoss.assignment.restservice.models.inventory.mappers.ShrubRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShrubsRepo {

    private final JdbcTemplate database;

    public ShrubsRepo(@Qualifier("inventoryJdbcTemplate") JdbcTemplate database) {
        this.database = database;
    }

    public List<Shrub> findAll() {
        String sql = "select * from shrubs";
        return database.query(sql,new ShrubRowMapper());
    }

    public Shrub findById(String id) {
        String sql = "select * from shrubs where product_code = ?";
        return database.queryForObject(sql, new Object[]{id}, new ShrubRowMapper());
    }

    public Shrub create(Shrub shrub) {
        String sql = "insert into shrubs values (?,?,?,?)";
        database.update(sql,shrub.getId(),shrub.getDescription(), shrub.getQuantity(),shrub.getPrice());
        return shrub;
    }

    public Shrub updateById(String id, Shrub byId) {
        String sql = "update shrubs set description = ?, quantity = ?, price = ?" +
                "where product_code = ?";
        database.update(sql,byId.getDescription(),byId.getQuantity(),byId.getPrice(), id);
        return byId;
    }

    public void deleteById(String id) {
        String sql = "delete from shrubs where product_code = ?";
        database.update(sql, id);
    }
}
