package aoss.assignment.restservice.models.inventory.mappers;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.ReferenceMaterial;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReferenceMaterialRowMapper implements RowMapper<ReferenceMaterial> {

    @Override
    public ReferenceMaterial mapRow(ResultSet resultSet, int i) throws SQLException {

        ReferenceMaterial referenceMaterial = new ReferenceMaterial();
        referenceMaterial.setId(resultSet.getString("productid"));
        referenceMaterial.setDescription(resultSet.getString("productdescription"));
        referenceMaterial.setQuantity(resultSet.getInt("productquantity"));
        referenceMaterial.setPrice(resultSet.getDouble("productprice"));

        return referenceMaterial;
    }
}
