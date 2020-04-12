package aoss.assignment.restservice.models.inventory.mappers;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Genomic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenomicRowMapper implements RowMapper<Genomic> {

    @Override
    public Genomic mapRow(ResultSet resultSet, int i) throws SQLException {

        Genomic genomic = new Genomic();
        genomic.setId(resultSet.getString("productid"));
        genomic.setDescription(resultSet.getString("productdescription"));
        genomic.setQuantity(resultSet.getInt("productquantity"));
        genomic.setPrice(resultSet.getDouble("productprice"));

        return genomic;
    }
}
