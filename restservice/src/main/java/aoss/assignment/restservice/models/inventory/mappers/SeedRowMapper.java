package aoss.assignment.restservice.models.inventory.mappers;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Seed;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeedRowMapper implements RowMapper<Seed> {

    @Override
    public Seed mapRow(ResultSet resultSet, int i) throws SQLException {

        Seed seed = new Seed();
        seed.setId(resultSet.getString("product_code"));
        seed.setDescription(resultSet.getString("description"));
        seed.setQuantity(resultSet.getInt("quantity"));
        seed.setPrice(resultSet.getDouble("price"));

        return seed;

    }
}
