package aoss.assignment.restservice.models.users.mappers;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 14.04.2020 */

import aoss.assignment.restservice.models.users.AuthLog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthLogRowMapper implements RowMapper<AuthLog> {

    @Override
    public AuthLog mapRow(ResultSet resultSet, int i) throws SQLException {

        AuthLog authLog = new AuthLog();
        authLog.setId(resultSet.getInt("id"));
        authLog.setLoginTime(resultSet.getString("login_time"));
        authLog.setLogoutTime(resultSet.getString("logout_time"));
        authLog.setUserId(resultSet.getInt("user_id"));

        return authLog;
    }
}
