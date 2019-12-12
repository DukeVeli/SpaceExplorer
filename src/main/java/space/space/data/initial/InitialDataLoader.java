package space.space.data.initial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import space.space.data.models.Role;
import space.space.data.models.User;
import space.space.data.repositories.RoleRepository;
import space.space.data.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static space.space.data.models.Roles.ROLE_ADMIN;
import static space.space.data.models.Roles.ROLE_USER;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        createRoleIfNotFound(ROLE_ADMIN.toString());
        createRoleIfNotFound(ROLE_USER.toString());

        alreadySetup = true;
    }



    @Transactional
    Role createRoleIfNotFound(String name) {

        Role role = roleRepository.findByAuthority(name);
        if (role == null) {
            role = new Role();
            role.setAuthority(name);
            roleRepository.save(role);
        }
        return role;
    }
}

