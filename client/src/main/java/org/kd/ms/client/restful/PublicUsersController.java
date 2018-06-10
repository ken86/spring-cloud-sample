package org.kd.ms.client.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public/users")
public class PublicUsersController {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    UserCrudService userCrudService;

    @PostMapping("/register")
    public String register(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        User user = new User(username, username, password);
        userCrudService.save(user);
        return login(username, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        return userAuthService.login(username, password)
                .orElseThrow(() -> new RuntimeException("invalid login"));
    }
}
