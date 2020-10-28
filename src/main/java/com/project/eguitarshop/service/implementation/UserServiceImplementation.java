package com.project.eguitarshop.service.implementation;

import com.project.eguitarshop.model.Role;
import com.project.eguitarshop.model.User;
import com.project.eguitarshop.model.exceptions.UserExistsException;
import com.project.eguitarshop.model.exceptions.UserNotFoundException;
import com.project.eguitarshop.repository.RoleRepository;
import com.project.eguitarshop.repository.UserRepository;
import com.project.eguitarshop.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImplementation(UserRepository userRepository
    ,PasswordEncoder passwordEncoder
    ,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
    }

    @Override
    public User findById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("%d",userId))
        );
    }
    @Override
    public User findByName(String userId) {
        return this.userRepository.findByUsername(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("%d",userId))
        );
    }


    @Override
    public User registerUser(User user) {
       /* if(this.userRepository.existsById(user.getId())){
            throw new UserExistsException(user.getUsername());
        }
        List<User> users=this.userRepository.findAll();
        for (User u : users){
            if(u.getUsername().equals(user.getUsername())){
              throw new UserExistsException(user.getUsername());
            }
        }*/
        List<Role> roleList=new LinkedList<>();
        Role role=this.roleRepository.findByName("USER");
        roleList.add(role);
        role=this.roleRepository.findByName("ROLE_USER");
        roleList.add(role);
        user.setRoles(roleList);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
       return this.userRepository.save(user);
    }


    @Override
    public User registerAdmin() {

        User admin = new User();
        List<Role> roleList=new LinkedList<>();
        Role role=this.roleRepository.findByName("ROLE_ADMIN");
        roleList.add(role);
        role=this.roleRepository.findByName("ROLE_ACTUATOR");
        roleList.add(role);
        role=this.roleRepository.findByName("ROLE_USER");
        roleList.add(role);
        role=this.roleRepository.findByName("ADMIN");
        roleList.add(role);
        role=this.roleRepository.findByName("USER");
        roleList.add(role);

        admin.setUsername("admin");
        admin.setPassword(this.passwordEncoder.encode("admin"));
        admin.setRoles(roleList);

        admin.setAccountNonExpired(true);
        admin.setAccountNonLocked(true);
        admin.setCredentialsNonExpired(true);
        admin.setEnabled(true);

        return this.userRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
