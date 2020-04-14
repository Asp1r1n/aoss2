package aoss.assignment.restservice.models.users.mappers;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 14.04.2020 */

import aoss.assignment.restservice.models.users.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("u_login"));
        user.setPassword(resultSet.getString("u_password"));
        user.setToken(resultSet.getString("token"));

        return user;
    }
}
