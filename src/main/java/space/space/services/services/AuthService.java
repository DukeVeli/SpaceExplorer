package space.space.services.services;

import space.space.data.models.CreditType;
import space.space.services.models.auth.LoginUserServiceModel;
import space.space.services.models.auth.RegisterUserServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel model) throws Exception;

    LoginUserServiceModel login(RegisterUserServiceModel serviceModel) throws Exception;


}
