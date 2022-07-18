package com.antoniocmoura.pixbank.infrastructure.configuration.usecases;

import com.antoniocmoura.pixbank.application.bankaccount.create.CreateBankAccountUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.create.DefaultCreateBankAccountUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.delete.DefaultDeleteBankAccountUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.delete.DeleteBankAccountUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.retrieve.get.DefaultGetBankAccountByIdUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.retrieve.get.GetBankAccountByIdUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.retrieve.list.DefaultListBankAccountUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.retrieve.list.ListBankAccountUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.update.DefaultUpdateBankAccountUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.update.UpdateBankAccountUseCase;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankAccountUseCaseConfig {

    private final BankAccountGateway bankAccountGateway;

    public BankAccountUseCaseConfig(final BankAccountGateway bankAccountGateway) {
        this.bankAccountGateway = bankAccountGateway;
    }

    @Bean
    public CreateBankAccountUseCase createBankAccountUseCase() {
        return new DefaultCreateBankAccountUseCase(bankAccountGateway);
    }

    @Bean
    public GetBankAccountByIdUseCase getBankAccountByIdUseCase() {
        return new DefaultGetBankAccountByIdUseCase(bankAccountGateway);
    }

    @Bean
    public ListBankAccountUseCase listBankAccountUseCase() {
        return new DefaultListBankAccountUseCase(bankAccountGateway);
    }

    @Bean
    public UpdateBankAccountUseCase updateBankAccountUseCase() {
        return new DefaultUpdateBankAccountUseCase(bankAccountGateway);
    }

    @Bean
    public DeleteBankAccountUseCase deleteBankAccountUseCase() {
        return new DefaultDeleteBankAccountUseCase(bankAccountGateway);
    }

}
