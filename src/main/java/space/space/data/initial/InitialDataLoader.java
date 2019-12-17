package space.space.data.initial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import space.space.data.models.Role;
import space.space.data.repositories.RoleRepository;

import static space.space.data.models.Roles.*;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) return;

        createRoleIfNotFound(ROLE_ROOT.toString());
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

