package space.space.services.services.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import space.space.data.models.CreditAccount;
import space.space.data.models.User;
import space.space.data.repositories.CreditAccountRepository;
import space.space.services.factories.CreditAccountFactory;
import space.space.services.services.CreditAccountService;

@Service
@AllArgsConstructor
public class CreditAccountServiceImpl implements CreditAccountService {
    private final CreditAccountRepository creditAccountRepository;
    private final CreditAccountFactory factory;




    @Override
    public CreditAccount create(User user) {
        CreditAccount account = factory.create();
        account.setUser(user);
        return creditAccountRepository.saveAndFlush(account);
    }
}
