package space.space.services.models.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserServiceModel {
    private String username;
    private String planetName;
    private double CreditCount;

    public LoginUserServiceModel(String username, String planetName) {
        this.username = username;
        this.planetName = planetName;
    }
}
