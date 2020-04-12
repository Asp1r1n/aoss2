package aoss.assignment.restservice.repos.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Seed;
import aoss.assignment.restservice.models.inventory.mappers.SeedRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeedsRepo {

    private final JdbcTemplate database;

    public SeedsRepo(@Qualifier("inventoryJdbcTemplate") JdbcTemplate database) {
        this.database = database;
    }


    public List<Seed> findAll() {
        String sql = "select * from seeds";
        return database.query(sql,new SeedRowMapper());
    }

    public Seed findById(String id) {
        String sql = "select * from seeds where product_code = ?";
        return database.queryForObject(sql, new Object[]{id}, new SeedRowMapper());
    }

    public Seed create(Seed seed) {
        String sql = "insert into seeds values (?,?,?,?)";
        database.update(sql,seed.getId(),seed.getDescription(), seed.getQuantity(),seed.getPrice());
        return seed;
    }

    public Seed updateById(String id, Seed byId) {
        String sql = "update seeds set description = ?, quantity = ?, price = ?" +
                "where product_code = ?";
        database.update(sql,byId.getDescription(),byId.getQuantity(),byId.getPrice(), id);
        return byId;
    }

    public void deleteById(String id) {
        String sql = "delete from seeds where product_code = ?";
        database.update(sql, id);
    }
}
