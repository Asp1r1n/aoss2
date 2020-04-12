package aoss.assignment.restservice.repos.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Genomic;
import aoss.assignment.restservice.models.inventory.mappers.CultureBoxRowMapper;
import aoss.assignment.restservice.models.inventory.mappers.GenomicRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenomicsRepo {

    private final JdbcTemplate database;

    public GenomicsRepo(@Qualifier("inventoryJdbcTemplate") JdbcTemplate database) {
        this.database = database;
    }


    public List<Genomic> findAll() {
        String sql = "select * from genomics";
        return database.query(sql,new GenomicRowMapper());
    }


    public Genomic findById(String id) {
        String sql = "select * from genomics where productid = ?";
        return database.queryForObject(sql, new Object[]{id}, new GenomicRowMapper());
    }

    public Genomic create(Genomic genomic) {
        String sql = "insert into genomics values (?,?,?,?)";
        database.update(sql,genomic.getId(),genomic.getDescription(),genomic.getQuantity(),genomic.getPrice());
        return genomic;
    }

    public Genomic updateById(String id, Genomic byId) {
        String sql = "update genomics set productdescription = ?, productquantity = ?, productprice = ?" +
                "where productid = ?";
        database.update(sql,byId.getDescription(),byId.getQuantity(),byId.getPrice(), id);
        return byId;
    }

    public void deleteById(String id) {
        String sql = "delete from genomics where productid = ?";
        database.update(sql, id);
    }
}
