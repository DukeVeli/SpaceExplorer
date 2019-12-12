package space.space.services.factories.impl;

import space.space.config.annotations.Factory;
import space.space.data.models.CreditAccount;
import space.space.data.models.CreditType;
import space.space.services.factories.CreditAccountFactory;

import static space.space.services.factories.Constants.INITIAL_CREDIT_AMOUNT;


@Factory
public class CreditAccountFactoryImpl implements CreditAccountFactory {
    @Override
    public CreditAccount create() {
        CreditAccount account = new CreditAccount();
        account.setCreditAmount(INITIAL_CREDIT_AMOUNT);
        account.setCreditType(CreditType.SPACE);

        return account;
    }
}
