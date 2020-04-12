package aoss.assignment.restservice.repos.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.CultureBox;
import aoss.assignment.restservice.models.inventory.mappers.CultureBoxRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CultureBoxesRepo {

    private final JdbcTemplate database;

    public CultureBoxesRepo(@Qualifier("inventoryJdbcTemplate") JdbcTemplate database) {
        this.database = database;
    }

    public List<CultureBox> findAll(){
        String sql = "select * from cultureboxes";
        return database.query(sql,new CultureBoxRowMapper());
    }

    public CultureBox findById(String id) {
        String sql = "select * from cultureboxes where productid = ?";
        return database.queryForObject(sql, new Object[]{id}, new CultureBoxRowMapper());
    }

    public CultureBox findByCultureBox(CultureBox cultureBox){
        String sql = "select * from cultureboxes where productid = ?" +
                " and productdescription = ? and productquantity = ?" +
                " and productprice = ?";
        return database.queryForObject(sql,
                new Object[]{
                        cultureBox.getId(),
                        cultureBox.getDescription(),
                        cultureBox.getPrice(),
                        cultureBox.getQuantity()}, new CultureBoxRowMapper());
    }


    public CultureBox create(CultureBox cultureBox) {
        String sql = "insert into cultureboxes values (?,?,?,?)";
        database.update(sql,cultureBox.getId(),cultureBox.getDescription(),cultureBox.getQuantity(),cultureBox.getPrice());
        return cultureBox;
    }

    public CultureBox updateById(String id, CultureBox cultureBox) {
        String sql = "update cultureboxes set productdescription = ?, productquantity = ?, productprice = ?" +
                "where productid = ?";
        database.update(sql,cultureBox.getDescription(),cultureBox.getQuantity(),cultureBox.getPrice(), id);
        return cultureBox;
    }

    public void deleteById(String id) {
        String sql = "delete from cultureboxes where productid = ?";
        database.update(sql, id);
    }
}
