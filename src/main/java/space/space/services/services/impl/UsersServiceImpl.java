package space.space.services.services.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import space.space.data.models.User;
import space.space.data.repositories.CreditAccountRepository;
import space.space.data.repositories.PlanetRepository;
import space.space.data.repositories.UserRepository;
import space.space.services.services.CreditAccountService;
import space.space.services.services.PlanetService;
import space.space.services.services.UsersService;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final PlanetRepository planetRepository;
    private final CreditAccountRepository creditAccountRepository;
    private final PlanetService planetService;
    private final CreditAccountService creditAccountService;
    private final ModelMapper mapper;

    /*@Override
    public void createPlanetForUser(String username, Planet planet) throws Exception {
        User user = usersRepository.findByUsername(username);
        Planet planet = mapper.map(planetService.create(.getName()),Planet.class);
        *//*planet.setUser(user);
        planetRepository.saveAndFlush(planet);*//*
        user.setPlanet(planet);
        usersRepository.saveAndFlush(user);
    }*/

    /*@Override
    public void createAccountForUser(String username) throws Exception {
        User user = usersRepository.findByUsername(username);
        CreditAccount account = creditAccountService.create();
      *//* account.setUser(user);
       creditAccountRepository.saveAndFlush(account);*//*
        user.setCreditAccount(account);
        usersRepository.saveAndFlush(user);
    }*/

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        Set<GrantedAuthority> authorities = new HashSet<>(user.getAuthorities());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}