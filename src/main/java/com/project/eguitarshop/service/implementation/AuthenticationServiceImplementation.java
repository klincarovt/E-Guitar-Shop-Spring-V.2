package com.project.eguitarshop.service.implementation;

import com.project.eguitarshop.model.Role;
import com.project.eguitarshop.model.User;
import com.project.eguitarshop.model.exceptions.PasswordsDontMatchException;
import com.project.eguitarshop.repository.RoleRepository;
import com.project.eguitarshop.repository.UserRepository;
import com.project.eguitarshop.service.AuthenticationService;
import com.project.eguitarshop.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserService userService;

    public AuthenticationServiceImplementation(UserRepository userRepository,
                                               PasswordEncoder passwordEncoder,
                                               RoleRepository roleRepository,
                                               UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public String getCurrentUserId() {
        return this.getCurrentUser().getUsername();
    }

    @Override
    public User signUpUser(String username, String password, String repeatedPassword) {
        if(!password.equals(repeatedPassword)){
            throw new PasswordsDontMatchException();
        }
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);

        List<Role> roleList = new LinkedList<>();
        Role role=this.roleRepository.findByName("ROLE_USER");
        roleList.add(role);
        role=this.roleRepository.findByName("USER");
        roleList.add(role);

        user.setRoles(roleList);

        return this.userService.registerUser(user);
    }


    /*@PostConstruct
    public void init() {
        if (!this.userRepository.existsById(1L)) {
            this.userService.registerAdmin();
        }
    }*/
}
