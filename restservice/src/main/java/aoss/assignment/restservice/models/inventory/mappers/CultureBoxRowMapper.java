package aoss.assignment.restservice.models.inventory.mappers;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.CultureBox;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CultureBoxRowMapper implements RowMapper<CultureBox> {

    @Override
    public CultureBox mapRow(ResultSet resultSet, int i) throws SQLException {

        CultureBox cultureBox = new CultureBox();
        cultureBox.setId(resultSet.getString("productid"));
        cultureBox.setDescription(resultSet.getString("productdescription"));
        cultureBox.setQuantity(resultSet.getInt("productquantity"));
        cultureBox.setPrice(resultSet.getDouble("productprice"));

        return cultureBox;
    }
}
