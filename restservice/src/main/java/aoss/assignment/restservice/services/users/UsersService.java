package aoss.assignment.restservice.services.users;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 14.04.2020 */

import aoss.assignment.restservice.models.users.User;
import aoss.assignment.restservice.repos.users.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService {

    private final UsersRepo usersRepo;

    public UsersService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public String login(String login, String password){

        String result = "";

        User user = usersRepo.findByLoginAndPassword(login,password);
        if(user != null){
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            usersRepo.updateById(user.getId(), user);
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


}
