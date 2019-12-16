package space.space.services.services;

import space.space.data.models.CreditAccount;
import space.space.data.models.CreditType;
import space.space.data.models.User;
import space.space.services.models.creditAccount.CreditAccountServiceModel;

public interface CreditAccountService {

    CreditAccount create(User user);

    void add(CreditAccountServiceModel model, String username);


}
