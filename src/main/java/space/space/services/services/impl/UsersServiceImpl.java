package space.space.services.services.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import space.space.data.models.CreditAccount;
import space.space.data.models.Role;
import space.space.data.models.User;
import space.space.data.repositories.CreditAccountRepository;
import space.space.data.repositories.RoleRepository;
import space.space.data.repositories.UserRepository;
import space.space.services.models.LogServiceModel;
import space.space.services.models.auth.UserServiceModel;
import space.space.services.services.LogService;
import space.space.services.services.RoleService;
import space.space.services.services.UsersService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final CreditAccountRepository creditAccountRepository;
    private final RoleRepository roleRepository;
    private final LogService logService;
    private final ModelMapper mapper;

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

    @Override
    public void spendMoney(String username, long spentPot) throws Exception {
        Optional<CreditAccount> optionalCreditAccount= creditAccountRepository.getByUserUsername(username);
       if (optionalCreditAccount.isEmpty()) {
           throw new Exception("No such Username");
       }
        CreditAccount account = optionalCreditAccount.get();
       account.setCreditAmount(account.getCreditAmount()-spentPot);
       creditAccountRepository.save(account);
    }

    @Override
    public void earnMoney(String username, long spentPot) throws Exception {
        Optional<CreditAccount> optionalCreditAccount= creditAccountRepository.getByUserUsername(username);
        if (optionalCreditAccount.isEmpty()) {
            throw new Exception("No such Username");
        }
        CreditAccount account = optionalCreditAccount.get();
        account.setCreditAmount(account.getCreditAmount()+spentPot);
        creditAccountRepository.save(account);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> this.mapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void promoteToAdmin(long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));

        Role role = roleRepository.getOne((long) 2);
        user.getAuthorities().add(role);

        LogServiceModel logServiceModel = new LogServiceModel();
        logServiceModel.setUsername(user.getUsername());
        logServiceModel.setDescription(user.getUsername()+" is now admin");
        logServiceModel.setTime(LocalDateTime.now());
        this.logService.saveLog(logServiceModel);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void demoteToUser(long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));

        Role role = roleRepository.getOne((long) 2);
        user.getAuthorities().remove(role);

        LogServiceModel logServiceModel = new LogServiceModel();
        logServiceModel.setUsername(user.getUsername());
        logServiceModel.setDescription(user.getUsername()+" is demoted");
        logServiceModel.setTime(LocalDateTime.now());
                user.getAuthorities().toArray();
        this.logService.saveLog(logServiceModel);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public boolean isAdmin(long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));
        UserServiceModel userServiceModel = this.mapper.map(user, UserServiceModel.class);
        user.getAuthorities().contains("USER_ADMIN");
        return userServiceModel.getAuthorities().size()>1;
    }
}