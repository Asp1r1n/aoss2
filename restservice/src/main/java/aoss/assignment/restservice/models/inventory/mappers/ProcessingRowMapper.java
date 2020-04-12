package aoss.assignment.restservice.models.inventory.mappers;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Processing;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcessingRowMapper implements RowMapper<Processing> {

    @Override
    public Processing mapRow(ResultSet resultSet, int i) throws SQLException {

        Processing processing = new Processing();
        processing.setId(resultSet.getString("productid"));
        processing.setDescription(resultSet.getString("productdescription"));
        processing.setQuantity(resultSet.getInt("productquantity"));
        processing.setPrice(resultSet.getDouble("productprice"));

        return processing;
    }
}
