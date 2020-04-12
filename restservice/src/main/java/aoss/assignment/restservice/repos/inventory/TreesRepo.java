package aoss.assignment.restservice.repos.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Tree;
import aoss.assignment.restservice.models.inventory.mappers.TreeRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TreesRepo {

    private final JdbcTemplate database;


    public TreesRepo(@Qualifier("inventoryJdbcTemplate") JdbcTemplate database) {
        this.database = database;
    }

    public List<Tree> findAll() {
        String sql = "select * from trees";
        return database.query(sql,new TreeRowMapper());
    }

    public Tree findById(String id) {
        String sql = "select * from trees where product_code = ?";
        return database.queryForObject(sql, new Object[]{id}, new TreeRowMapper());
    }

    public Tree create(Tree tree) {
        String sql = "insert into trees values (?,?,?,?)";
        database.update(sql,tree.getId(),tree.getDescription(), tree.getQuantity(),tree.getPrice());
        return tree;
    }

    public Tree updateById(String id, Tree byId) {
        String sql = "update trees set description = ?, quantity = ?, price = ?" +
                "where product_code = ?";
        database.update(sql,byId.getDescription(),byId.getQuantity(),byId.getPrice(), id);
        return byId;
    }

    public void deleteById(String id) {
        String sql = "delete from trees where product_code = ?";
        database.update(sql, id);
    }
}
