package aoss.assignment.restservice.repos.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.ReferenceMaterial;
import aoss.assignment.restservice.models.inventory.mappers.ProcessingRowMapper;
import aoss.assignment.restservice.models.inventory.mappers.ReferenceMaterialRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReferenceMaterialsRepo {

    private final JdbcTemplate database;

    public ReferenceMaterialsRepo(@Qualifier("inventoryJdbcTemplate") JdbcTemplate database) {
        this.database = database;
    }

    public List<ReferenceMaterial> findAll() {
        String sql = "select * from referencematerials";
        return database.query(sql,new ReferenceMaterialRowMapper());
    }

    public ReferenceMaterial findById(String id) {
        String sql = "select * from referencematerials where productid = ?";
        return database.queryForObject(sql, new Object[]{id}, new ReferenceMaterialRowMapper());
    }

    public ReferenceMaterial create(ReferenceMaterial referenceMaterial) {
        String sql = "insert into referencematerials values (?,?,?,?)";
        database.update(sql,referenceMaterial.getId(),referenceMaterial.getDescription(),
                referenceMaterial.getQuantity(),referenceMaterial.getPrice());
        return referenceMaterial;
    }

    public ReferenceMaterial updateById(String id, ReferenceMaterial byId) {
        String sql = "update referencematerials set productdescription = ?, productquantity = ?, productprice = ?" +
                "where productid = ?";
        database.update(sql,byId.getDescription(),byId.getQuantity(),byId.getPrice(), id);
        return byId;
    }

    public void deleteById(String id) {
        String sql = "delete from referencematerials where productid = ?";
        database.update(sql, id);
    }
}
