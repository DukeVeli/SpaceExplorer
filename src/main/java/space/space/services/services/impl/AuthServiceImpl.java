package space.space.services.services.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import space.space.data.models.Role;
import space.space.data.models.User;
import space.space.data.repositories.RoleRepository;
import space.space.data.repositories.UserRepository;
import space.space.services.models.auth.LoginUserServiceModel;
import space.space.services.models.auth.RegisterUserServiceModel;
import space.space.services.services.*;
import space.space.services.services.validation.AuthValidationService;

import java.util.Collections;

import static space.space.data.models.Roles.ROLE_ADMIN;
import static space.space.data.models.Roles.ROLE_USER;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthValidationService authValidationService;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final HashingService hashingService;
    private final UsersService usersService;
    private final PlanetService planetService;
    private final CreditAccountService creditAccountService;
    private final RoleRepository roleRepository;

    @Override
    public void register(RegisterUserServiceModel model) {
        if (!authValidationService.isValid(model)) {
            // do something
            return;
        }

        User user = mapper.map(model, User.class);
        user.setPlanet(null);
        user.setPassword(hashingService.hash(user.getPassword()));
         user.setAuthorities(Collections.singleton(roleRepository.findByAuthority(ROLE_USER.toString())));
        user = userRepository.saveAndFlush(user);
        planetService.create(user);
        creditAccountService.create(user);

       /* try {
            usersService.createAccountForUser(user.getUsername());
        } catch (Exception e) {e.printStackTrace();
*/

       /* try {
            usersService.createPlanetForUser(user.getUsername(),planetService.create(model.getPlanetName()));
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }

    @Override
    public LoginUserServiceModel login(RegisterUserServiceModel serviceModel) throws Exception {
        String passwordHash = hashingService.hash(serviceModel.getPassword());
        return userRepository
                .findByUsernameAndPassword(serviceModel.getUsername(), passwordHash)
                .map(user -> {
                    String planetName = user.getPlanet() == null
                            ? null
                            : user.getPlanet().getName();

                    return new LoginUserServiceModel(serviceModel.getUsername(), planetName);
                })
                .orElseThrow(() -> new Exception("Invalid user"));
    }
}
