package space.space.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import space.space.data.models.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    @Column (name = "username", nullable = false, unique = true, updatable = false)
    private String username;

    @Column (name = "password", nullable = false)
    private String password;

    @Column (name = "email", nullable = false, unique = true)
    private String email;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<GrantedAuthority> authorities;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, targetEntity = Planet.class)
    private Planet planet;

    @OneToOne(mappedBy = "user")
    private CreditAccount creditAccount;

    @Column
    @Transient
    private boolean isAccountNonExpired=true;

    @Column
    @Transient
    private boolean isAccountNonLocked=true;

    @Column
    @Transient
    private boolean isCredentialsNonExpired=true;

    @Column
    @Transient
    private boolean isEnabled=true;


}
