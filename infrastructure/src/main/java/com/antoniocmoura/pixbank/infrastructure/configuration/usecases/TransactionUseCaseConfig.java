package com.antoniocmoura.pixbank.infrastructure.configuration.usecases;

import com.antoniocmoura.pixbank.application.transaction.create.deposit.CreateDepositTransactionUseCase;
import com.antoniocmoura.pixbank.application.transaction.create.deposit.DefaultCreateDepositTransactionUseCase;
import com.antoniocmoura.pixbank.application.transaction.create.transfer.CreateTransferTransactionUseCase;
import com.antoniocmoura.pixbank.application.transaction.create.transfer.DefaultCreateTransferTransactionUseCase;
import com.antoniocmoura.pixbank.application.transaction.retrieve.get.DefaultGetTransactionByIdUseCase;
import com.antoniocmoura.pixbank.application.transaction.retrieve.get.GetTransactionByIdUseCase;
import com.antoniocmoura.pixbank.application.transaction.retrieve.list.DefaultTransactionListByBankAccountIdUseCase;
import com.antoniocmoura.pixbank.application.transaction.retrieve.list.DefaultTransactionListUseCase;
import com.antoniocmoura.pixbank.application.transaction.retrieve.list.TransactionListByBankAccountIdUseCase;
import com.antoniocmoura.pixbank.application.transaction.retrieve.list.TransactionListUseCase;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyGateway;
import com.antoniocmoura.pixbank.domain.transaction.TransactionGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionUseCaseConfig {

    private final TransactionGateway transactionGateway;
    private final BankAccountGateway bankAccountGateway;
    private final PixKeyGateway pixKeyGateway;

    public TransactionUseCaseConfig(
            final TransactionGateway transactionGateway,
            final BankAccountGateway bankAccountGateway,
            final PixKeyGateway pixKeyGateway
    ) {
        this.transactionGateway = transactionGateway;
        this.bankAccountGateway = bankAccountGateway;
        this.pixKeyGateway = pixKeyGateway;
    }

    @Bean
    public CreateTransferTransactionUseCase createTransferTransactionUseCase() {
        return new DefaultCreateTransferTransactionUseCase(
                this.transactionGateway,
                this.bankAccountGateway,
                this.pixKeyGateway
        );
    }

    @Bean
    public CreateDepositTransactionUseCase createDepositTransactionUseCase() {
        return new DefaultCreateDepositTransactionUseCase(
                this.transactionGateway,
                this.bankAccountGateway,
                this.pixKeyGateway
        );
    }

    @Bean
    public TransactionListUseCase transactionListUseCase() {
        return new DefaultTransactionListUseCase(this.transactionGateway);
    }

    @Bean
    public GetTransactionByIdUseCase getTransactionByIdUseCase() {
        return new DefaultGetTransactionByIdUseCase(this.transactionGateway);
    }

    @Bean
    public TransactionListByBankAccountIdUseCase transactionListByBankAccountIdUseCase() {
        return new DefaultTransactionListByBankAccountIdUseCase(this.transactionGateway);
    }
}
