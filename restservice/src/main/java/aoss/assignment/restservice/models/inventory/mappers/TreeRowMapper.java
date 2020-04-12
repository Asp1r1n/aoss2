package aoss.assignment.restservice.models.inventory.mappers;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Tree;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TreeRowMapper implements RowMapper<Tree> {

    @Override
    public Tree mapRow(ResultSet resultSet, int i) throws SQLException {

        Tree tree = new Tree();
        tree.setId(resultSet.getString("product_code"));
        tree.setDescription(resultSet.getString("description"));
        tree.setQuantity(resultSet.getInt("quantity"));
        tree.setPrice(resultSet.getDouble("price"));

        return tree;
    }
}
