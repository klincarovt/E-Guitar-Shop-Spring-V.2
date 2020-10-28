package com.project.eguitarshop.web.RestController;

import com.project.eguitarshop.model.User;
import com.project.eguitarshop.service.AuthenticationService;
import com.project.eguitarshop.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserRestController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/{id}")
    public User findId(){
        return this.userService.findByName(this.authenticationService.getCurrentUserId());
    }
    @PostMapping
    public User save(@Valid User user)
    {
        return this.userService.registerUser(user);
    }


    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteById(@PathVariable Long id){
        this.userService.deleteById(id);
    }
}
