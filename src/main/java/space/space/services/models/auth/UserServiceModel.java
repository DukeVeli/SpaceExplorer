package space.space.services.models.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.space.services.models.RoleServiceModel;

import java.util.Set;



@Getter
@Setter
@NoArgsConstructor

public class UserServiceModel {
    private long id;
    private String username;
    private String password;
    private String email;
    private Set<RoleServiceModel> authorities;
}
