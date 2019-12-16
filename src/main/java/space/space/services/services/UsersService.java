package space.space.services.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
   void spendMoney(String username, double spentPot) throws Exception;
   void earnMoney(String username, double spentPot) throws Exception;
}
