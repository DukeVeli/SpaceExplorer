package space.space.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.space.data.models.CreditAccount;
import space.space.data.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String role);

}
