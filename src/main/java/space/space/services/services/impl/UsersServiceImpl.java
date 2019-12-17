package space.space.services.services.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import space.space.data.models.CreditAccount;
import space.space.data.models.User;
import space.space.data.repositories.CreditAccountRepository;
import space.space.data.repositories.PlanetRepository;
import space.space.data.repositories.UserRepository;
import space.space.services.services.CreditAccountService;
import space.space.services.services.PlanetService;
import space.space.services.services.UsersService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final CreditAccountRepository creditAccountRepository;
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
}