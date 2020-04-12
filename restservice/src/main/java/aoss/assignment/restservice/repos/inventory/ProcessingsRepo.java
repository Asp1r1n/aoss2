package aoss.assignment.restservice.repos.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Processing;
import aoss.assignment.restservice.models.inventory.mappers.ProcessingRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProcessingsRepo {

    private final JdbcTemplate database;

    public ProcessingsRepo(@Qualifier("inventoryJdbcTemplate") JdbcTemplate database) {
        this.database = database;
    }


    public List<Processing> findAll() {
        String sql = "select * from processing";
        return database.query(sql,new ProcessingRowMapper());
    }

    public Processing findById(String id) {
        String sql = "select * from processing where productid = ?";
        return database.queryForObject(sql, new Object[]{id}, new ProcessingRowMapper());
    }

    public Processing create(Processing processing) {
        String sql = "insert into processing values (?,?,?,?)";
        database.update(sql,processing.getId(),processing.getDescription(),processing.getQuantity(),processing.getPrice());
        return processing;
    }

    public Processing updateById(String id, Processing byId) {
        String sql = "update processing set productdescription = ?, productquantity = ?, productprice = ?" +
                "where productid = ?";
        database.update(sql,byId.getDescription(),byId.getQuantity(),byId.getPrice(), id);
        return byId;
    }

    public void deleteById(String id) {
        String sql = "delete from processing where productid = ?";
        database.update(sql, id);
    }
}
