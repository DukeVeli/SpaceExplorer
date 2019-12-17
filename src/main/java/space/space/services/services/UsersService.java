package space.space.services.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import space.space.services.models.auth.UserServiceModel;

import java.util.List;

public interface UsersService extends UserDetailsService {
   void spendMoney(String username, long spentPot) throws Exception;
   void earnMoney(String username, long spentPot) throws Exception;

   List<UserServiceModel> findAllUsers();
   void promoteToAdmin(long id);
   void demoteToUser(long id);

   boolean isAdmin(long id);
}
