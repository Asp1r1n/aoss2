package aoss.assignment.restservice.repos.users;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 14.04.2020 */

import aoss.assignment.restservice.models.users.User;
import aoss.assignment.restservice.models.users.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepo {

    private final JdbcTemplate database;

    public UsersRepo(@Qualifier("usersJdbcTemplate") JdbcTemplate database) {
        this.database = database;
    }

    public User findByLoginAndPassword(String login, String password){
        String sql = "select * from users where u_login = ? and u_password = ?";
        return database.queryForObject(sql, new Object[]{login,password}, new UserRowMapper());
    }

    public User findByToken(String token){
        String sql = "select * from users where token = ?";
        try {
            return database.queryForObject(sql, new Object[]{token}, new UserRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public User updateById(int id, User user) {
        String sql = "update users set u_login = ?, u_password = ?, token = ? where id = ?";
        database.update(sql,user.getLogin(),user.getPassword(),user.getToken(), id);
        return user;
    }
}
