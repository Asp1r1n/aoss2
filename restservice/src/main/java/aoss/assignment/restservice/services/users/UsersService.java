package aoss.assignment.restservice.services.users;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 14.04.2020 */

import aoss.assignment.restservice.models.users.AuthLog;
import aoss.assignment.restservice.models.users.User;
import aoss.assignment.restservice.repos.users.AuthLogsRepo;
import aoss.assignment.restservice.repos.users.UsersRepo;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService {

    private final UsersRepo usersRepo;
    private final AuthLogsRepo authLogsRepo;

    public UsersService(UsersRepo usersRepo, AuthLogsRepo authLogsRepo) {
        this.usersRepo = usersRepo;
        this.authLogsRepo = authLogsRepo;
    }

    public String login(String login, String password){

        String result = "";

        User user = usersRepo.findByLoginAndPassword(login,password);
        if(user != null){
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            usersRepo.updateById(user.getId(), user);
            authLogsRepo.login(AuthLog.authLogWithUserAndCurrentTime(user.getId()));
            result = token;
        }

        return result;
    }

    public org.springframework.security.core.userdetails.User findByToken(String token){

        User user = usersRepo.findByToken(token);
        if(user != null){
            org.springframework.security.core.userdetails.User authUser  =
                    new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),
                            true,true,true,true,
                            AuthorityUtils.createAuthorityList("USER"));
            return authUser;
        }

        return null;
    }

    public User logout(String token){
        User user = findUserByToken(token);
        if(user.getId() != 0){
            user.setToken("");
            user = updateUserById(user.getId(), user);
            authLogsRepo.logoutByUserId(user.getId(), AuthLog.currentTime());
        }
        return user;
    }

    public User findUserByToken(String token){
        User user = usersRepo.findByToken(token);
        return user != null ? user : new User();
    }

    public User updateUserById(Integer id, User user){
        return usersRepo.updateById(id,user);
    }


}
