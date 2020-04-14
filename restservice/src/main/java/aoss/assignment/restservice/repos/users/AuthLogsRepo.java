package aoss.assignment.restservice.repos.users;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 14.04.2020 */

import aoss.assignment.restservice.models.users.AuthLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthLogsRepo {

    private final JdbcTemplate database;

    public AuthLogsRepo(@Qualifier("usersJdbcTemplate") JdbcTemplate database) {
        this.database = database;
    }

    public void login(AuthLog authLog) {
        String sql = "insert into authlogs(login_time,logout_time,user_id) values (?,?,?)";
        database.update(sql, authLog.getLoginTime(), authLog.getLogoutTime(), authLog.getUserId());
    }

    public void logoutByUserId(Integer id, String time) {
        String sql = "update authlogs set logout_time = ? where logout_time = '' and user_id = ?";
        database.update(sql, time,id);
    }
}
