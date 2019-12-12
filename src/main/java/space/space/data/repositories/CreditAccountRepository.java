package space.space.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.space.data.models.CreditAccount;
import java.util.Optional;

public interface CreditAccountRepository  extends JpaRepository<CreditAccount, Long> {
    Optional<CreditAccount> getByUserUsername(String username);


}
