package space.space.services.models.accounts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserServiceModel {
    private String username;
    private String planetName;

    public LoginUserServiceModel(String username, String planetName) {
        this.username = username;
        this.planetName = planetName;
    }
}
