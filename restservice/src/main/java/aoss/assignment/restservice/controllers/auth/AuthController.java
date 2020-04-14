package aoss.assignment.restservice.controllers.auth;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 14.04.2020 */

import aoss.assignment.restservice.services.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
