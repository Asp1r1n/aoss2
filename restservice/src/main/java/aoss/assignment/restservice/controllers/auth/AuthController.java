package aoss.assignment.restservice.controllers.auth;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 14.04.2020 */

import aoss.assignment.restservice.models.users.User;
import aoss.assignment.restservice.services.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsersService usersService;

    public AuthController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/token")
    public String getToken(@RequestParam String login, @RequestParam String password){
        String token = usersService.login(login, password);
        return token.isEmpty() ? "Token not found" : token;
    }

    @PostMapping("/logout")
    public User logout(@RequestHeader("Authorization") String authorization){
        String token = authorization.substring(7).trim();
        User logout = usersService.logout(token);
        if(logout.getId() != 0){
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return logout;
    }
}
