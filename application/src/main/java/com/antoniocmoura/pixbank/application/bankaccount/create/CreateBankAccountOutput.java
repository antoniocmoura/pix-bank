package com.antoniocmoura.pixbank.application.bankaccount.create;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccount;

public record CreateBankAccountOutput(
        String id
) {

    public static CreateBankAccountOutput from(final String anId) {
        return new CreateBankAccountOutput(anId);
    }

    public static CreateBankAccountOutput from(final BankAccount aBankAccount) {
        return new CreateBankAccountOutput(
                aBankAccount.getId().getValue()
        );
    }


}
