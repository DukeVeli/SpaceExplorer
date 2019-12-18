package space.space.services.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import space.space.data.models.CreditAccount;
import space.space.data.models.CreditType;
import space.space.data.repositories.CreditAccountRepository;
import space.space.services.factories.CreditAccountFactory;
import space.space.services.factories.impl.CreditAccountFactoryImpl;
import space.space.services.models.creditAccount.CreditAccountServiceModel;
import space.space.services.services.CreditAccountService;
import space.space.services.services.LogService;

import static org.junit.jupiter.api.Assertions.*;

class CreditAccountServiceImplTest {

    @MockBean
    CreditAccountRepository creditAccountRepository;
    @MockBean
    LogService logService;

    @Test
    void checkIfCreateWorksProperly() {
        CreditAccountFactory factory = new CreditAccountFactoryImpl();
        CreditAccount account = new CreditAccount();
        account.setCreditAmount(10000);
        account.setCreditType(CreditType.SPACE);
        account.setId(1);

        CreditAccount toCheck = factory.create();
        assertEquals(account.getCreditAmount(),toCheck.getCreditAmount());
        assertEquals(account.getCreditType(),toCheck.getCreditType());
    }



    @Test
    void checkIfAddWorksProperly() throws Exception {
        CreditAccountServiceModel model = new CreditAccountServiceModel();
        model.setCreditAmount(10);
        model.setCreditType(CreditType.BGN);
        model.setUsername("Pesho");
        CreditAccountService creditAccountService = new CreditAccountServiceImpl(creditAccountRepository,logService,new CreditAccountFactoryImpl());




    }

    @Test
    void checkIfAddThrowsException() throws Exception {
        CreditAccountServiceModel model = new CreditAccountServiceModel();
        model.setCreditAmount(-10);
        model.setCreditType(CreditType.BGN);

        CreditAccountService creditAccountService = new CreditAccountServiceImpl(creditAccountRepository,logService,new CreditAccountFactoryImpl());


        Assertions.assertThrows(Exception.class, () -> {
            creditAccountService.add(model, "Pesho" );
        });

    }

    @Test
    void addDailyCredit() {
    }
}