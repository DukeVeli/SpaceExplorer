package space.space.services.services;

import space.space.services.models.auth.LoginUserServiceModel;
import space.space.services.models.auth.RegisterUserServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel model);

    LoginUserServiceModel login(RegisterUserServiceModel serviceModel) throws Exception;
}
