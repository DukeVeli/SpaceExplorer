package space.space.services.services.validation.impl;

import org.springframework.stereotype.Service;
import space.space.data.repositories.UserRepository;
import space.space.services.models.auth.RegisterUserServiceModel;
import space.space.services.services.validation.AuthValidationService;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {
    private final UserRepository userRepository;

    public AuthValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(RegisterUserServiceModel user) {
        return this.isEmailValid(user.getEmail()) &&
                this.arePasswordsValid(user.getPassword(), user.getConfirmPassword()) &&
                this.isUsernameFree(user.getUsername());
    }

    private boolean arePasswordsValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isUsernameFree(String username) {
        return !userRepository.existsByUsername(username);
    }

    private boolean isEmailValid(String email) {
        return true;
    }
}
