package space.space.services.factories.impl;

import org.junit.jupiter.api.Test;
import space.space.data.models.CreditAccount;
import space.space.data.models.CreditType;
import space.space.services.factories.CreditAccountFactory;

import static org.junit.jupiter.api.Assertions.*;

class CreditAccountFactoryImplTest {

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
}