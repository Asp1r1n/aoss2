package aoss.assignment.restservice.models.inventory.mappers;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Shrub;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShrubRowMapper implements RowMapper<Shrub> {

    @Override
    public Shrub mapRow(ResultSet resultSet, int i) throws SQLException {

        Shrub shrub = new Shrub();
        shrub.setId(resultSet.getString("product_code"));
        shrub.setDescription(resultSet.getString("description"));
        shrub.setQuantity(resultSet.getInt("quantity"));
        shrub.setPrice(resultSet.getDouble("price"));

        return shrub;
    }
}
