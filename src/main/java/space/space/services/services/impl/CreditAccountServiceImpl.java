package space.space.services.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import space.space.data.models.CreditAccount;
import space.space.data.models.CreditType;
import space.space.data.models.User;
import space.space.data.repositories.CreditAccountRepository;
import space.space.services.factories.CreditAccountFactory;
import space.space.services.models.LogServiceModel;
import space.space.services.models.creditAccount.CreditAccountServiceModel;
import space.space.services.services.CreditAccountService;
import space.space.services.services.LogService;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreditAccountServiceImpl implements CreditAccountService {
    private final CreditAccountRepository creditAccountRepository;
    private final LogService logService;
    private final CreditAccountFactory factory;

    @Override
    public CreditAccount create(User user) {
        CreditAccount account = factory.create();
        account.setUser(user);
        return creditAccountRepository.saveAndFlush(account);
    }

    @Override
    public void add(CreditAccountServiceModel model, String username) throws Exception {
        long moneyToAdd =0;
        CreditType type=model.getCreditType();
        long money= model.getCreditAmount();
        if (money<0){throw new Exception("Can't add negative amount of money");}

        switch (type){
            case BGN: moneyToAdd+=money; break;
            case EUR: moneyToAdd+=money*3; break;
            case USD: moneyToAdd+=money*2; break;
        }
        CreditAccount account = creditAccountRepository.getByUserUsername(username).get();
        account.setCreditAmount(account.getCreditAmount()+moneyToAdd);
        creditAccountRepository.saveAndFlush(account);

        LogServiceModel logServiceModel = new LogServiceModel();
        logServiceModel.setUsername(username);
        logServiceModel.setDescription("Added "+money+" type "+type);
        logServiceModel.setTime(LocalDateTime.now());
        logService.saveMoneyLog(logServiceModel);
    }
}
